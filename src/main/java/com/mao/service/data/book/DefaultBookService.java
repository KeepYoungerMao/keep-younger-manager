package com.mao.service.data.book;

import com.mao.entity.ViewType;
import com.mao.entity.data.book.Book;
import com.mao.entity.data.book.BookChapter;
import com.mao.entity.data.book.BookParam;
import com.mao.mapper.data.BookMapper;
import com.mao.service.BaseService;
import com.mao.util.SU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * 古籍处理
 * @author zongx at 2020/3/7 20:03
 */
@Service
public class DefaultBookService extends BaseService implements BookService {

    private BookMapper bookMapper;

    @Autowired
    public void setBookMapper(BookMapper bookMapper){
        this.bookMapper = bookMapper;
    }

    /**
     * 查询古籍列表
     * @param bookParam 古籍参数
     * @return 古籍列表
     */
    @Override
    public List<Book> getBooks(BookParam bookParam) {
        Integer page = bookParam.getPage();
        transPageParam(bookParam);
        Long total = bookMapper.getBookTotalPage(bookParam);
        bookParam.setTotal(total > 0 ? SU.ceil(total,bookParam.getLimit()) : 1);
        List<Book> books = bookMapper.getBooks(bookParam);
        bookParam.setPage(page);
        return books;
    }

    /**
     * 古籍详情的改动
     * 详情界面参数：type：类型：查看详情进入；新建进入；更新成功进入；新建成功进入；
     * @param id 古籍id
     * @param book 新建或更新时的传入
     * @param type 改动类型
     */
    @Override
    public void changeBook(Long id, Book book, ViewType type, Model model) {
        String msg;
        switch (type){
            case SHOW:
                if (SU.isZero(id)) {
                    type = ViewType.SHOW_ERROR;
                    msg = "缺少id";
                    break;
                }
                Book _book = bookMapper.getBookById(id);
                if (null == _book) {
                    type = ViewType.SHOW_ERROR;
                    msg = "无效id";
                } else {
                    book = _book;
                    msg = "ok";
                }
                break;
            case CREATE:
                String s1 = checkBook(book);
                if (null != s1){
                    type = ViewType.CREATE_FAILED;
                    msg = s1;
                    break;
                }
                book.setUpdate(System.currentTimeMillis());
                bookMapper.saveBook(book);
                type = ViewType.CREATE_SUCCESS;
                msg = "ok";
                break;
            case UPDATE:
                String s = checkBook(book);
                if (null != s){
                    type = ViewType.UPDATE_FAILED;
                    msg = s;
                    break;
                }
                book.setUpdate(System.currentTimeMillis());
                bookMapper.updateBook(book);
                type = ViewType.UPDATE_SUCCESS;
                msg = "ok";
                break;
            default:
                type = ViewType.SHOW_ERROR;
                msg = "请求错误";
                break;
        }
        model.addAttribute("changeType", type);
        model.addAttribute("book",book);
        model.addAttribute("changeMsg",msg);
    }

    /**
     * 古籍数据的检查
     * @param book 古籍
     * @return null / 错误提示
     */
    private String checkBook(Book book){
        if (SU.isEmpty(book.getName()))
            return "名称不能为空";
        if (SU.isEmpty(book.getAuth()))
            return "作者不能为空";
        return null;
    }

    /**
     * 查询古籍章节列表
     * @param id 古籍id
     * @return 古籍章节列表
     */
    @Override
    public List<BookChapter> getBookChapters(Long id) {
        if (null == id || id <= 0)
            return new ArrayList<>();
        return bookMapper.getBookChapters(id);
    }

}

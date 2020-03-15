package com.mao.service.data.book;

import com.mao.entity.data.book.Book;
import com.mao.entity.data.book.BookChapter;
import com.mao.entity.data.book.BookParam;
import com.mao.mapper.data.BookMapper;
import com.mao.service.BaseService;
import com.mao.util.SU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @return 古籍详情
     */
    @Override
    public Book changeBook(Long id, Book book, String type) {
        //TODO 改进
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

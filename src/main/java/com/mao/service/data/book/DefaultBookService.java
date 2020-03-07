package com.mao.service.data.book;

import com.mao.entity.data.book.Book;
import com.mao.entity.data.book.BookParam;
import com.mao.mapper.data.BookMapper;
import com.mao.service.BaseService;
import com.mao.util.SU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}

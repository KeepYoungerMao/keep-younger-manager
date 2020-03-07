package com.mao.service.data.book;

import com.mao.entity.data.book.Book;
import com.mao.entity.data.book.BookParam;

import java.util.List;

/**
 * 古籍处理
 * @author zongx at 2020/3/7 20:02
 */
public interface BookService {

    /**
     * 查询古籍列表
     * @param bookParam 古籍参数
     * @return 古籍列表
     */
    List<Book> getBooks(BookParam bookParam);

}

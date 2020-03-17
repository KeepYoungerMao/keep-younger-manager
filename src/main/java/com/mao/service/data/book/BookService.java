package com.mao.service.data.book;

import com.mao.entity.ViewType;
import com.mao.entity.data.book.Book;
import com.mao.entity.data.book.BookChapter;
import com.mao.entity.data.book.BookParam;
import org.springframework.ui.Model;

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

    /**
     * 古籍详情的改动
     * @param id 古籍id
     * @param book 新建或更新时的传入
     * @param type 改动类型
     */
    void changeBook(Long id, Book book, ViewType type, Model model);

    /**
     * 查询古籍章节列表
     * @param id 古籍id
     * @return 古籍章节列表
     */
    List<BookChapter> getBookChapters(Long id);

}

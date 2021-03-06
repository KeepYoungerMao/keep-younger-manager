package com.mao.mapper.data;

import com.mao.entity.data.book.Book;
import com.mao.entity.data.book.BookChapter;
import com.mao.entity.data.book.BookParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 古籍数据请求
 * @author zongx at 2020/1/11 19:31
 */
@Repository
@Mapper
public interface BookMapper {

    /**
     * search books data
     * @param bookParam book param
     * @return books data
     */
    List<Book> getBooks(BookParam bookParam);

    /**
     * search books total page
     * @param bookParam book param
     * @return total page
     */
    Long getBookTotalPage(BookParam bookParam);

    /**
     * search book by id
     * @param id id
     * @return book data
     */
    Book getBookById(@Param("id") Long id);

    /**
     * update book data
     * @param book book data
     */
    void updateBook(Book book);

    /**
     * save book data
     * @param book book data
     */
    void saveBook(Book book);

    /**
     * search book chapter data list
     * @param id book chapter id
     * @return book chapter data list
     */
    List<BookChapter> getBookChapters(@Param("id") Long id);

}

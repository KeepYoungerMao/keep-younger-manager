package com.mao.web;

import com.mao.entity.data.book.Book;
import com.mao.entity.data.book.BookParam;
import com.mao.service.data.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据管理 请求
 * @author zongx at 2020/1/11 20:24
 */
@Controller
@RequestMapping("data")
public class DataController {

    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService){
        this.bookService = bookService;
    }

    /**
     * 查询古籍列表
     * @param bookParam 古籍参数
     * @return 古籍列表
     */
    @RequestMapping("book")
    public String getBooks(BookParam bookParam, Model model){
        List<Book> books = bookService.getBooks(bookParam);
        model.addAttribute("bookParam",bookParam);
        model.addAttribute("books",books);
        return "data/book/book";
    }

}

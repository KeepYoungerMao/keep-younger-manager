package com.mao.web;

import com.mao.entity.data.bjx.Bjx;
import com.mao.entity.data.bjx.BjxParam;
import com.mao.entity.data.book.Book;
import com.mao.entity.data.book.BookParam;
import com.mao.entity.data.buddhist.Buddhist;
import com.mao.entity.data.buddhist.BuddhistParam;
import com.mao.service.data.bjx.BjxService;
import com.mao.service.data.book.BookService;
import com.mao.service.data.buddhist.BuddhistService;
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
    private BuddhistService buddhistService;
    private BjxService bjxService;

    @Autowired
    public void setBookService(BookService bookService){
        this.bookService = bookService;
    }
    @Autowired
    public void setBuddhistService(BuddhistService buddhistService){
        this.buddhistService = buddhistService;
    }
    @Autowired
    public void setBjxService(BjxService bjxService){
        this.bjxService = bjxService;
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

    /**
     * 查询佛经列表
     * @param buddhistParam 佛经参数
     * @return 佛经列表
     */
    @RequestMapping("buddhist")
    public String getBuddhists(BuddhistParam buddhistParam, Model model){
        List<Buddhist> buddhists = buddhistService.getBuddhists(buddhistParam);
        model.addAttribute("",buddhistParam);
        model.addAttribute("",buddhists);
        return "data/buddhist/buddhist";
    }

    /**
     * 查询百家姓列表
     * @param bjxParam 百家姓参数
     * @return 百家姓列表
     */
    @RequestMapping("bjx")
    public String getBjx(BjxParam bjxParam, Model model){
        List<Bjx> bjx = bjxService.getBjx(bjxParam);
        model.addAttribute("",bjxParam);
        model.addAttribute("",bjx);
        return "data/bjx/bjx";
    }

}

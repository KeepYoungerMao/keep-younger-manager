package com.mao.web;

import com.mao.entity.data.book.BookParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 数据管理 请求
 * @author zongx at 2020/1/11 20:24
 */
@Controller
@RequestMapping("data")
public class DataController {

    //@LogAop
    //@PreAuthorize("hasAnyAuthority('data_book')")
    @GetMapping("book")
    public String book(){
        return "data/book/book";
    }

    @PreAuthorize("hasAnyAuthority('data_book')")
    @PostMapping("book")
    public String searchBook(@RequestBody BookParam bookParam){
        return "data/book/book";
    }

}
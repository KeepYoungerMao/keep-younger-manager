package com.mao.web;

import com.mao.entity.data.book.BookParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 数据管理 请求
 * @author zongx at 2020/1/11 20:24
 */
@Controller
@RequestMapping("data")
public class DataController {

    @GetMapping("book/{id}")
    public String book(@PathVariable("id") String id){
        return "data/book/book";
    }

    @PostMapping("book")
    public String searchBook(@RequestBody BookParam bookParam){
        return "data/book/book";
    }

}

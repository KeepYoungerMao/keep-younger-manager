package com.mao.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 页码参数
 * @author zongx at 2020/3/4 15:27
 */
@Getter
@Setter
public class Page {

    private Integer page;           //页码
    private Long total;             //总页码数
    private Integer limit;          //限制条数

    public Page(){
        this.page = 0;
        this.limit = 0;
    }

}

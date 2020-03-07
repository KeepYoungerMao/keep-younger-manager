package com.mao.entity.data.book;

import com.mao.entity.Page;
import lombok.Getter;
import lombok.Setter;

/**
 * 古籍查询参数
 * @author zongx at 2020/1/11 20:41
 */
@Getter
@Setter
public class BookParam extends Page {

    private String name;            //名称
    private String auth;            //作者
    private Integer type;           //类型
    private Integer dynasty;        //朝代
    private Boolean free;           //是否免费
    private Boolean off_sale;       //是否下架

}

package com.mao.entity.data.book;

import lombok.Getter;
import lombok.Setter;

/**
 * 古籍表
 * @author zongx at 2020/1/11 18:15
 */
@Getter
@Setter
public class Book {
    private Long id;                //主键
    private String name;            //名称
    private String auth;            //作者
    private String image;           //图片
    private String s_image;         //小图片
    private String intro;           //详情
    private String type;            //类型
    private Integer type_id;        //类型id
    private String dynasty;         //朝代
    private Integer dynasty_id;     //朝代id
    private Integer count;          //阅读频率
    private Boolean free;           //是否免费
    private Boolean off_sale;       //是否下架
    private Long update;            //更新日期
    private Boolean delete;         //删除标识
}

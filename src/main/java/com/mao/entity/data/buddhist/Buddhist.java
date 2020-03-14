package com.mao.entity.data.buddhist;

import lombok.Getter;
import lombok.Setter;

/**
 * 佛经数据包装类
 * table: tt_buddhist
 * @author zongx at 2020/3/14 22:11
 */
@Getter
@Setter
public class Buddhist {

    private Long id;                //主键
    private String name;            //名称
    private String auth;            //作者
    private String image;           //图片地址
    private String intro;           //介绍
    private String type;            //类型
    private Integer type_id;        //类型id
    private Long update;            //更新日期
    private Boolean delete;         //删除标识

}

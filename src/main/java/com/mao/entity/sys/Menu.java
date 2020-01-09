package com.mao.entity.sys;

import lombok.Getter;
import lombok.Setter;

/**
 * 菜单类
 * @author zongx at 2020/1/9 20:56
 */
@Getter
@Setter
public class Menu {
    private Long id;        //菜单id
    private Long pid;       //父类菜单id
    private String name;    //菜单名称
    private String icon;    //菜单图标
    private String url;     //菜单链接
}

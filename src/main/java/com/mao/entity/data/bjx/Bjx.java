package com.mao.entity.data.bjx;

import lombok.Getter;
import lombok.Setter;

/**
 * 百家姓 包装类
 * table: tt_bjx
 * @author zongx at 2020/3/14 22:51
 */
@Getter
@Setter
public class Bjx {

    private Long id;            //主键
    private String name;        //名称
    private String py;          //拼音首字母
    private String src;         //详情
    private Long update;            //更新日期
    private Boolean delete;         //删除标识

}

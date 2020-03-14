package com.mao.entity.data.bjx;

import com.mao.entity.Page;
import lombok.Getter;
import lombok.Setter;

/**
 * 百家姓参数
 * @author zongx at 2020/3/14 22:54
 */
@Getter
@Setter
public class BjxParam extends Page {

    private String name;    //名称
    private String py;      //拼音首字母

}

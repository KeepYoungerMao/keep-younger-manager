package com.mao.entity.sys;

import lombok.Getter;
import lombok.Setter;

/**
 * 系统日志参数查询包装类
 * @author mao by 10:46 2020/2/13
 */
@Getter
@Setter
public class LogParam {

    private Long user_id;           //用户id
    private String data_type;       //数据类型
    private String process_type;    //操作类型
    private Long start_time;        //开始时间
    private Long end_time;          //结束时间
    private Integer limit;          //限制条数

}
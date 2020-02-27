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
    private String user_name;       //用户登录名
    private String data_type;       //数据类型
    private String process_type;    //操作类型
    private Long start_time;        //开始时间
    private Long end_time;          //结束时间
    private Boolean process_access; //操作是否成功
    private Integer page;           //页码
    private Integer limit;          //限制条数

    public LogParam(){
        this.page = 0;
        this.limit = 0;
    }

}
package com.mao.entity.sys;

import lombok.Getter;
import lombok.Setter;

/**
 * 日志封装类
 * @author mao by 17:14 2020/2/11
 */
@Getter
@Setter
public class Log {

    private Long id;                        //主键
    private Long user_id;                   //用户id
    private String user_login;              //用户登录名
    private Long user_role;                 //用户角色id
    private String account_ip;              //操作者ip
    private String request_url;             //请求路url
    private String process_name;            //事件名称
    private String process_type;            //时间类型
    private Boolean process_access;         //操作是否成功
    private Long process_date;              //操作时间

    public Log(){

    }

}
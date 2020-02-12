package com.mao.entity.sys;

import lombok.Getter;
import lombok.Setter;

/**
 * 登录登出日志封装类
 * @author mao by 10:39 2020/2/12
 */
@Getter
@Setter
public class LoginLog {

    private Long id;                    //主键
    private Long user_id;               //用户id
    private String user_login;          //用户登陆名
    private String account_ip;          //操作者ip
    private String account_address;     //操作者城市
    private LoginEnum login_type;       //登录登出类型
    private Long login_date;            //登录登出时间

}
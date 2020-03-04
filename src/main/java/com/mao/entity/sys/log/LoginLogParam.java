package com.mao.entity.sys.log;

import com.mao.entity.Page;
import lombok.Getter;
import lombok.Setter;

/**
 * 登录日志信息
 * @author zongx at 2020/3/4 15:24
 */
@Getter
@Setter
public class LoginLogParam extends Page {

    private Long user_id;
    private String user_name;       //用户登录名
    private String login_type;      //登录类型
    private Long start_time;        //开始时间
    private Long end_time;          //结束时间

}

package com.mao.service.log;

import com.mao.entity.sys.LoginEnum;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志管理
 * @author mao by 16:32 2020/2/11
 */
public interface LogService {

    //系统操作日志的保存
    void saveLog(HttpServletRequest request, String username, boolean access);

    //用户登录登出日志的保存
    void saveLoginLog(HttpServletRequest request, String username, LoginEnum type, String address);

}
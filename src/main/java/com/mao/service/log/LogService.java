package com.mao.service.log;

import com.mao.entity.sys.log.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 日志管理
 * @author mao by 16:32 2020/2/11
 */
public interface LogService {

    //系统操作日志的保存
    void saveLog(HttpServletRequest request, String username, boolean access);

    //用户登录登出日志的保存
    void saveLoginLog(HttpServletRequest request, String username, LoginEnum type);

    //查询系统操作日志(根据用户id，数据类型，操作类型，操作时间，限制条数等)
    List<Log> getLogs(LogParam logParam);

    //查询系统登录日志（根据用户id、登陆类型、登陆时间、限制条数等）
    List<LoginLog> getLoginLogs(LoginLogParam loginLogParam);

}
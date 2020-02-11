package com.mao.service.log;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志管理
 * @author mao by 16:32 2020/2/11
 */
public interface LogService {

    //日志的保存
    void saveLog(HttpServletRequest request, String username, boolean access);

}
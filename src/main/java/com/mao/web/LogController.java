package com.mao.web;

import com.mao.entity.sys.log.*;
import com.mao.service.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 日志请求处理
 * @author mao by 11:03 2020/2/13
 */
@Controller
@RequestMapping("log")
public class LogController {

    private LogService logService;

    @Autowired
    public void setLogService(LogService logService){
        this.logService = logService;
    }

    /**
     * 查询系统日志列表
     * @param logParam 系统日志参数
     */
    @RequestMapping("sys")
    public String sysLog(LogParam logParam, Model model){
        List<Log> logs = logService.getLogs(logParam);
        model.addAttribute("logParam",logParam);
        model.addAttribute("logs",logs);
        return "sys/log/log";
    }

    /**
     * 查询系统登录日志列表
     * @param loginLogParam 登录日志参数
     * @param model model
     */
    @RequestMapping("login")
    public String sysLoginLog(LoginLogParam loginLogParam, Model model){
        List<LoginLog> loginLogs = logService.getLoginLogs(loginLogParam);
        model.addAttribute("loginLogParam",loginLogParam);
        model.addAttribute("loginLogs",loginLogs);
        return "sys/log/loginLog";
    }

    /**
     * 查询系统邮件日志列表
     * @param emailLogParam 邮件日志参数
     * @param model model
     */
    @RequestMapping("email")
    public String sysEmailLog(EmailLogParam emailLogParam, Model model){
        List<EmailLog> emailLogs = logService.getEmailLogs(emailLogParam);
        model.addAttribute("emailLogParam",emailLogParam);
        model.addAttribute("emailLogs",emailLogs);
        return "sys/log/emailLog";
    }

}
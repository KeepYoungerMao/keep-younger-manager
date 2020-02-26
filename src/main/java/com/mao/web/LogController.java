package com.mao.web;

import com.mao.entity.sys.Log;
import com.mao.entity.sys.LogParam;
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
     */
    @RequestMapping("sys")
    public String sysLog(LogParam logParam, Model model){
        List<Log> logs = logService.getLogs(logParam);
        model.addAttribute("logParam",logParam);
        model.addAttribute("logs",logs);
        return "sys/log/log";
    }

}
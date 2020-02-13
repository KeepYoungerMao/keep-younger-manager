package com.mao.web;

import com.mao.entity.sys.LogParam;
import com.mao.mapper.response.ResponseData;
import com.mao.service.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志请求处理
 * @author mao by 11:03 2020/2/13
 */
@RestController
@RequestMapping("v/log")
public class LogController {

    private LogService logService;

    @Autowired
    public void setLogService(LogService logService){
        this.logService = logService;
    }

    /**
     * 获取系统日志列表
     * @param logParam 日志查询参数
     * @return 系统日志列表
     */
    @GetMapping("sys")
    public ResponseData getSysLogs(LogParam logParam){
        return logService.getLogs(logParam);
    }

}
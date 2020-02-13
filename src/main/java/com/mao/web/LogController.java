package com.mao.web;

import com.mao.entity.sys.LogParam;
import com.mao.mapper.response.ResponseData;
import com.mao.service.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志请求处理
 * @author mao by 11:03 2020/2/13
 */
@RestController
public class LogController {

    private LogService logService;

    @Autowired
    public void setLogService(LogService logService){
        this.logService = logService;
    }

    /**
     * TODO 无法通过
     */
    @PostMapping("v/log/sys")
    public ResponseData sysLog(@RequestBody LogParam logParam){
        return logService.getLogs(logParam);
    }

}
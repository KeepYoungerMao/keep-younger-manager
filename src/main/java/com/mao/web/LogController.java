package com.mao.web;

import com.mao.entity.sys.LogParam;
import com.mao.mapper.response.ResponseData;
import com.mao.service.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 查询系统日志列表
     */
    @PostMapping("sys")
    public @ResponseBody ResponseData sysLog(@RequestBody LogParam logParam){
        return logService.getLogs(logParam);
    }

}
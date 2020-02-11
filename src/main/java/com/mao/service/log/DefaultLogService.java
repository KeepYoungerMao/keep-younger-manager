package com.mao.service.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 日志管理
 * @author mao by 18:03 2020/1/8
 */
@Service
public class DefaultLogService {

    private static final Logger log = LoggerFactory.getLogger(DefaultLogService.class);

    public void afterSuccess(){
        log.info("{} after success");
    }

}
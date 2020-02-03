package com.mao.service.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author mao by 18:03 2020/1/8
 */
@Aspect
@Service
public class LogService {

    private static final Logger log = LoggerFactory.getLogger(LogService.class);

    @Pointcut("@annotation(LogAop)")
    public void cut(){}

    @AfterReturning("cut()")
    public void afterSuccess(JoinPoint joinPoint){
        log.info("{} after success", joinPoint);
    }

}
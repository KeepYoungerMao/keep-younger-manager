package com.mao.service.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

/**
 * @author mao by 18:03 2020/1/8
 */
@Aspect
@Service
public class LogService {

    @Pointcut("@annotation(LogAop)")
    public void cut(){}

    @Around("cut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        System.out.println(proceed);
        return proceed;
    }

}
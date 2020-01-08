package com.mao.service.aop;

import java.lang.annotation.*;

/**
 * @author mao by 18:01 2020/1/8
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAop {
}
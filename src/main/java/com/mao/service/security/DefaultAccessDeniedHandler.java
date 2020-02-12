package com.mao.service.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权失败的处理
 * spring security配置中
 * HttpSecurity.exceptionHandling().accessDeniedHandler(defaultAccessDeniedHandler)
 * 自定义处理授权失败后的逻辑
 * 如果授权失败后直接跳转至授权失败页面时，可配置：
 * HttpSecurity.exceptionHandling().accessDeniedPage("/auth")
 * 可完成同样逻辑
 * @author zongx at 2020/2/3 14:33
 */
@Service
public class DefaultAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger log = LoggerFactory.getLogger(DefaultAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        log.info("[ {} ] 授权失败 ", request.getRequestURI());
        response.sendRedirect("/auth");
    }

}

package com.mao.service.security;

import com.mao.entity.sys.log.LoginEnum;
import com.mao.service.log.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户登入成功处理器
 * @author mao by 16:51 2020/1/8
 */
@Service
public class DefaultAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(DefaultAuthenticationSuccessHandler.class);

    private LogService logService;

    @Autowired
    public void setLogService(LogService logService){
        this.logService = logService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {
        String username = null;
        if (authentication.getPrincipal() instanceof UserDetails){
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = userDetails.getUsername();
        }
        //登陆类型、登录地点 待升级
        logService.saveLoginLog(request,username, LoginEnum.PASSWORD_LOGIN);
        log.info("[ {} ] 登陆成功",username);
        response.sendRedirect("/");
    }
}
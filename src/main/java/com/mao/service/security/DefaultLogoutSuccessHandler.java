package com.mao.service.security;

import com.mao.entity.sys.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户退出成功处理器
 * @author mao by 17:19 2020/1/8
 */
@Service
public class DefaultLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        System.out.println(((User)authentication.getPrincipal()).getUsername()+"退出成功");
        response.sendRedirect("/login");
    }
}
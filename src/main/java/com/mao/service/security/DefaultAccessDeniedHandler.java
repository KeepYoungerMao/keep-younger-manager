package com.mao.service.security;

import com.mao.service.BaseService;
import com.mao.util.JsonUtil;
import com.mao.util.SU;
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
public class DefaultAccessDeniedHandler extends BaseService implements AccessDeniedHandler {

    private static final String REQUEST_TYPE = "REQUEST-TYPE";
    private static final String REQUEST_TYPE_VALUE = "ajax";

    /**
     * 授权失败处理方法
     *
     * 前端发送ajax请求时，header中发送专有参数REQUEST-TYPE=ajax
     * 方法中判断有此参数时发送json数据
     * 没有此参数则返回auth.html页面
     *
     * @param request request
     * @param response response
     * @param e exception
     * @throws IOException e
     * @throws ServletException s
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        String header = request.getHeader(REQUEST_TYPE);
        if (SU.isNotEmpty(header) && REQUEST_TYPE_VALUE.equals(header)){
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            String json = JsonUtil.obj2json(permission("无权限"));
            response.getWriter().write(null == json ? "response error" : json);
        } else {
            response.sendRedirect("/auth");
        }
    }

}

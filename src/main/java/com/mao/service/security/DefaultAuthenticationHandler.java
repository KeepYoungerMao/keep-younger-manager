package com.mao.service.security;

import com.mao.util.HttpUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * 权限授权方法
 * @author mao by 14:00 2020/2/11
 */
@Service("DefaultAuthenticationHandler")
public class DefaultAuthenticationHandler {

    private AntPathMatcher antPathMatcher;

    /**
     * 权限验证
     * 自定义权限验证
     * spring security中，使用：
     *      http.authorizeRequests().antMatchers("/**").authenticated()
     *      可以进行验证，使用@HasAuthority('xx')可以对方法授权拦截
     *      使用自定义授权方法是需要进行日志收集
     * @param request request
     * @param authentication 当前登录用户
     * @return 是否有权限
     */
    public boolean hasAuthority(HttpServletRequest request, Authentication authentication){
        boolean hasAuthority = false;
        System.out.println(HttpUtil.getAccountIp(request));
        if (authentication.getPrincipal() instanceof UserDetails){
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            if (doMatcher(request.getRequestURI(),authorities))
                hasAuthority = true;
        }
        if (hasAuthority){
            System.out.println(request.getRequestURI()+" 认证成功");
        } else {
            System.out.println(request.getRequestURI()+" 认证失败");
        }
        return hasAuthority;
    }

    /**
     * 使用spring的AntPathMatcher进行url匹配
     * @param url 请求url
     * @param patterns 匹配路径
     * @return 是否匹配
     */
    private boolean doMatcher(String url, Collection<? extends GrantedAuthority> patterns){
        AntPathMatcher instance = getInstance();
        for (GrantedAuthority authority : patterns)
            if (instance.match(authority.getAuthority(),url))
                return true;
        return false;
    }

    /**
     * 获取AntPathMatcher实例
     * @return AntPathMatcher
     */
    private AntPathMatcher getInstance(){
        if (null == antPathMatcher)
            antPathMatcher = new AntPathMatcher();
        return antPathMatcher;
    }

}
package com.mao.config;

import com.mao.service.security.DefaultAuthenticationSuccessHandler;
import com.mao.service.security.DefaultLogoutSuccessHandler;
import com.mao.service.security.SecurityUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * spring security 配置类
 * @author mao by 14:13 2020/1/6
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private SecurityUserDetailService securityUserDetailService;
    private DefaultAuthenticationSuccessHandler defaultAuthenticationSuccessHandler;
    private DefaultLogoutSuccessHandler defaultLogoutSuccessHandler;
    //private DefaultAccessDeniedHandler defaultAccessDeniedHandler;

    @Autowired
    public void setSecurityUserDetailService(SecurityUserDetailService securityUserDetailService){
        this.securityUserDetailService = securityUserDetailService;
    }
    @Autowired
    public void setDefaultAuthenticationSuccessHandler(DefaultAuthenticationSuccessHandler handler){
        this.defaultAuthenticationSuccessHandler = handler;
    }
    @Autowired
    public void setDefaultLogoutSuccessHandler(DefaultLogoutSuccessHandler handler){
        this.defaultLogoutSuccessHandler = handler;
    }
    /*@Autowired
    public void setDefaultAccessDeniedHandler(DefaultAccessDeniedHandler handler){
        this.defaultAccessDeniedHandler = handler;
    }*/

    @Bean
    public PasswordEncoder passwordEncoder(){
        //BCrypt强哈希方法
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证
     * @param auth 认证管理器
     * @throws Exception e
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityUserDetailService)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 授权
     * 认证失败采用自定义处理器：用于记录操作事件
     * 登录登出采用自定义处理器，用于记录用户登录登出事件
     * access() 方法：自定义授权方法
     *   使用自定义授权方法是需要记录用户访问资源成功或失败的日志
     *   使用后可以去除.exceptionHandling()方法（当时使用此方法获取用户授权失败时的日志）
     * @param http 授权配置
     * @throws Exception e
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login","/error","/login-error","/auth","/logout")
                .permitAll()
                .antMatchers("/**")
                .access("@DefaultAuthenticationHandler.hasAuthority(request,authentication)")
                //.authenticated()
                //.and()
                //.exceptionHandling()
                //.accessDeniedHandler(defaultAccessDeniedHandler)
                //.accessDeniedPage("/auth")
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login-error")
                //.defaultSuccessUrl("/",true)
                .successHandler(defaultAuthenticationSuccessHandler)
                .and()
                .logout()
                .logoutSuccessHandler(defaultLogoutSuccessHandler)
                .and()
                .rememberMe()
                .rememberMeParameter("remember");
    }

    /**
     * 资源配置
     * @param web web
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .mvcMatchers("/static/**");
    }

}
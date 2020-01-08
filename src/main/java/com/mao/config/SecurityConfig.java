package com.mao.config;

import com.mao.service.security.DefaultAuthenticationSuccessHandler;
import com.mao.service.security.DefaultLogoutSuccessHandler;
import com.mao.service.security.SecurityUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private SecurityUserDetailService securityUserDetailService;
    private DefaultAuthenticationSuccessHandler defaultAuthenticationSuccessHandler;
    private DefaultLogoutSuccessHandler defaultLogoutSuccessHandler;

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
     * @param http 授权配置
     * @throws Exception e
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login","/error","/login-error","/auth","/logout")
                .permitAll()
                .antMatchers("/**")
                .authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/auth")
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
     * @throws Exception e
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .mvcMatchers("/static/**");
    }

}
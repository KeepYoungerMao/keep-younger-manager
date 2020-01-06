package com.mao.service.security;

import com.mao.entity.sys.User;
import com.mao.service.sys.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * spring security认证实现
 * @author mao by 14:18 2020/1/6
 */
@Service
public class SecurityUserDetailService implements UserDetailsService {

    private SystemService systemService;
    @Autowired
    public void setSystemService(SystemService systemService){
        this.systemService = systemService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = systemService.getUserByUsername(username);
        if (null == user) throw new UsernameNotFoundException("cannot load message by this username");
        return user;
    }

}
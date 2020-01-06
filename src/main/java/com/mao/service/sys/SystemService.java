package com.mao.service.sys;

import com.mao.entity.sys.User;

/**
 * 系统
 * @author mao by 15:05 2020/1/6
 */
public interface SystemService {

    //根据登录名查询用户信息
    User getUserByUsername(String username);

}
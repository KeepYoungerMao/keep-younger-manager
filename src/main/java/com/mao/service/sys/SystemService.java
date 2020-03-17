package com.mao.service.sys;

import com.mao.entity.sys.Menu;
import com.mao.entity.sys.User;
import com.mao.entity.response.ResponseData;

import java.util.List;

/**
 * 系统
 * @author mao by 15:05 2020/1/6
 */
public interface SystemService {

    //根据登录名查询用户信息
    User getUserByUsername(String username, boolean needAuthority);

    //获取用户列表
    ResponseData getUsers(String kw);

    //获取所有菜单
    List<Menu> getAllMenu();

}
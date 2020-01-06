package com.mao.service.sys;

import com.mao.entity.sys.Permission;
import com.mao.entity.sys.User;
import com.mao.mapper.sys.RolePermissionMapper;
import com.mao.mapper.sys.UserMapper;
import com.mao.util.SU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统
 * @author mao by 15:05 2020/1/6
 */
@Service
public class DefaultSystemService implements SystemService {

    private UserMapper userMapper;
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper){
        this.userMapper = userMapper;
    }
    @Autowired
    public void setRolePermissionMapper(RolePermissionMapper rolePermissionMapper){
        this.rolePermissionMapper = rolePermissionMapper;
    }

    /**
     * 根据登录名查询用户信息
     * 此方法仅查询认证信息。用于认证服务
     * @param username 用户名
     * @return User
     */
    @Override
    public User getUserByUsername(String username) {
        User user = userMapper.getUserByUsername(username);
        if (null != user){
            List<Permission> permissions = rolePermissionMapper.getPermissionByUsername(username);
            if (SU.isNotEmpty(permissions)){
                user.setPermissions(permissions);
                return user;
            }
        }
        return null;
    }

}
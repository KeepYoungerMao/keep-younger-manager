package com.mao.service.sys;

import com.mao.config.Cache;
import com.mao.entity.sys.Menu;
import com.mao.entity.sys.Permission;
import com.mao.entity.sys.SimpleUser;
import com.mao.entity.sys.User;
import com.mao.mapper.response.ResponseData;
import com.mao.mapper.sys.RolePermissionMapper;
import com.mao.mapper.sys.UserMapper;
import com.mao.service.BaseService;
import com.mao.util.SU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统
 * @author mao by 15:05 2020/1/6
 */
@Service
public class DefaultSystemService extends BaseService implements SystemService {

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
     * @param needAuthority 是否需要请求权限数据
     * @return User
     */
    @Override
    public User getUserByUsername(String username, boolean needAuthority) {
        User user = userMapper.getUserByUsername(username);
        if (null != user){
            if (needAuthority){
                List<Permission> permissions = rolePermissionMapper.getPermissionByUsername(username);
                if (SU.isNotEmpty(permissions)){
                    user.setPermissions(permissions);
                    return user;
                }
            }
            return user;
        }
        return null;
    }

    /**
     * 查询用户列表
     * 用于以用户id为查询条件的列表提供
     * @param kw 关键词
     * @return 用户列表
     */
    @Override
    public ResponseData getUsers(String kw) {
        if (SU.isEmpty(kw))
            return ok(null);
        List<SimpleUser> users = userMapper.getUsers(kw);
        return ok(users);
    }

    /**
     * 获取所有菜单数据
     * 返回缓存的菜单数据
     * @return 菜单列表
     */
    @Override
    public List<Menu> getAllMenu() {
        return Cache.MENU;
    }

}
package com.mao.mapper.sys;

import com.mao.entity.sys.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色权限
 * @author mao by 15:14 2020/1/6
 */
@Repository
@Mapper
public interface RolePermissionMapper {

    //根据登录名查询该用户权限信息
    List<Permission> getPermissionByUsername(@Param("username") String username);

}
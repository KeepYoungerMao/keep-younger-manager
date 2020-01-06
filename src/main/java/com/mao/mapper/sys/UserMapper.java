package com.mao.mapper.sys;

import com.mao.entity.sys.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户
 * @author mao by 15:09 2020/1/6
 */
@Repository
@Mapper
public interface UserMapper {

    //根据登录名查询用户信息
    User getUserByUsername(@Param("username") String username);

}
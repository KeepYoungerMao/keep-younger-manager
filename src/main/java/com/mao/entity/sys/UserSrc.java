package com.mao.entity.sys;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户详情表
 * @author zongx at 2020/1/9 20:39
 */
@Getter
@Setter
public class UserSrc {

    private Long id;
    private String full_name;
    private String username;
    private String password;
    private Boolean locked;
    private Boolean expired;
    private Boolean enabled;
    private Long role_id;
    private Long role_name;
    private String company;
    private String dept;
    private String note;
    private String image;
    private String idcard;
    private String address;
    private String qq;
    private String wx;
    private String phone;
    private String email;

}

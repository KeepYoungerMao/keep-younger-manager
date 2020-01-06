package com.mao.entity.sys;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * 用户表
 * @author mao by 14:35 2020/1/6
 */
@Getter
@Setter
public class User implements UserDetails {

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

    private List<Permission> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !expired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
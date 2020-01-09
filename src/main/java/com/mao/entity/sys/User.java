package com.mao.entity.sys;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * 用户表
 * 用于认证
 * @author mao by 14:35 2020/1/6
 */
@Getter
@Setter
@ToString
public class User implements UserDetails {

    private Long id;
    private String full_name;
    private String username;
    private String password;
    private Boolean locked;
    private Boolean expired;
    private Boolean enabled;

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
package com.mao.entity.sys;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

/**
 * 权限表
 * @author mao by 14:56 2020/1/6
 */
@Getter
@Setter
public class Permission implements GrantedAuthority {
    private Long id;
    private String name;
    private String intro;

    @Override
    public String getAuthority() {
        return name;
    }
}
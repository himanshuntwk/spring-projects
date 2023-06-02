package com.himanshu.springsecuritydemo.appwrappers;

import com.himanshu.springsecuritydemo.entity.AppRole;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class AppRoleWrapper implements GrantedAuthority {

    private AppRole appRole;

    @Override
    public String getAuthority() {
        return appRole.getName();
    }
}

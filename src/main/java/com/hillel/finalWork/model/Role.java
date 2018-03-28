package com.hillel.finalWork.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_CUSTOMER, ROLE_MANAGER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}

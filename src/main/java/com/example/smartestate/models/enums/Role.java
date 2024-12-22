package com.example.smartestate.models.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    AGENT,
    CUSTOMER,
    DEVELOPER;

    @Override
    public String getAuthority() {
        return name();
    }
}

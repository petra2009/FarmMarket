package com.example.FarmMarket.DAO.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@AllArgsConstructor
public enum Role implements GrantedAuthority {
    USER("user"),
    ADMIN("admin");

    private String name;

    @Override
    public String getAuthority() {
        return name();
    }
}

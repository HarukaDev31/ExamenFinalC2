package com.example.enums;

import java.util.*;

public enum UserRole {
    USER(Collections.emptySet()),
    ADMIN(Set.of(
            Permission.ADMIN_READ,
            Permission.ADMIN_UPDATE,
            Permission.ADMIN_DELETE,
            Permission.ADMIN_CREATE
    ));

    private  Set<Permission> permissions;

    UserRole(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public  Set<Permission> getPermissions() {
        return permissions;
    }

}
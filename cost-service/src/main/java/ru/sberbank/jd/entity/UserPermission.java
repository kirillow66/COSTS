package ru.sberbank.jd.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * The enum User permission.
 */
@RequiredArgsConstructor
@Getter
public enum UserPermission {
    /**
     * Admin read user permission.
     */
    ADMIN_READ("admin:read"),
    /**
     * Admin update user permission.
     */
    ADMIN_UPDATE("admin:update"),
    /**
     * Admin create user permission.
     */
    ADMIN_CREATE("admin:create"),
    /**
     * Admin delete user permission.
     */
    ADMIN_DELETE("admin:delete"),
    /**
     * Owner read user permission.
     */
    OWNER_READ("owner:read"),
    /**
     * Owner update user permission.
     */
    OWNER_UPDATE("owner:update"),
    /**
     * Owner create user permission.
     */
    OWNER_CREATE("owner:create"),
    /**
     * Owner delete user permission.
     */
    OWNER_DELETE("owner:delete");

    private final String permission;
}

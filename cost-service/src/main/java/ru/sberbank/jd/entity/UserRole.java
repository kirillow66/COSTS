package ru.sberbank.jd.entity;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * The enum User role.
 */
@RequiredArgsConstructor
@Getter
public enum UserRole {
    /**
     * User user role.
     */
    USER(Collections.emptySet()),
    /**
     * Admin user role.
     */
    ADMIN( Set.of(
            UserPermission.ADMIN_READ,
            UserPermission.ADMIN_UPDATE,
            UserPermission.ADMIN_CREATE,
            UserPermission.ADMIN_DELETE)),
    /**
     * Owner user role.
     */
    OWNER( Set.of(
            UserPermission.OWNER_READ,
            UserPermission.OWNER_CREATE,
            UserPermission.OWNER_UPDATE,
            UserPermission.OWNER_DELETE
    ));


    private final Set<UserPermission> permissions;

    /**
     * Gets authorities.
     *
     * @return the authorities
     */
    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }    
}

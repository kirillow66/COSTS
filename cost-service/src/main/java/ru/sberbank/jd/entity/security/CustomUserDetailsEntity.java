package ru.sberbank.jd.entity.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.sberbank.jd.entity.User;
import ru.sberbank.jd.entity.UserRole;

/**
 * The type Custom user details entity.
 */
public class CustomUserDetailsEntity implements UserDetails {

    private User user;

    /**
     * Instantiates a new Custom user details entity.
     *
     * @param user the user
     */
    public CustomUserDetailsEntity(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRole userRoles : user.getUserRoles()) {
            authorities.addAll(userRoles.getAuthorities());
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }
    
}

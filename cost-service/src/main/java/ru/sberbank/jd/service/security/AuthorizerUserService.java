package ru.sberbank.jd.service.security;

import java.util.UUID;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.sberbank.jd.entity.User;
import ru.sberbank.jd.entity.security.CustomUserDetailsEntity;

/**
 * The type Authorizer user service.
 */
@Component
public class AuthorizerUserService {
    /**
     * Is principal id boolean.
     *
     * @param userId the user id
     * @return the boolean
     */
    public boolean isPrincipalId(UUID userId) {
        CustomUserDetailsEntity principal =
                (CustomUserDetailsEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = principal.getUser();
        return user.getId().equals(userId);
    }

    /**
     * Is logged boolean.
     *
     * @return the boolean
     */
    public boolean isLogged() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && !(auth instanceof AnonymousAuthenticationToken) && auth.isAuthenticated();
    }

    /**
     * Gets principal id.
     *
     * @return the principal id
     */
    public UUID getPrincipalId() {
        CustomUserDetailsEntity principal =
                (CustomUserDetailsEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = principal.getUser();
        return user.getId();
    }

    /**
     * Gets principal.
     *
     * @return the principal
     */
    public User getPrincipal() {
        CustomUserDetailsEntity principal =
                (CustomUserDetailsEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getUser();
    }

}

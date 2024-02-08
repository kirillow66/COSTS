package ru.sberbank.jd.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.entity.User;
import ru.sberbank.jd.entity.UserRole;
import ru.sberbank.jd.exception.UserFound;
import ru.sberbank.jd.repository.UserRepository;

/**
 * The type User service.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    @Override
    @PostAuthorize("returnObject.id == @customAuthorizerFilter.isPrincipal() or hasRole('ADMIN')")
    public User findById(UUID id) {  
        Optional<User> byId = repository.findById(id);
        return byId.orElse(null);
    }

    /**
     * Find by login optional.
     *
     * @param login the login
     * @return the optional
     */
    @Override
    @PostAuthorize("returnObject.id == @customAuthorizerFilter.isPrincipal() or hasRole('ADMIN')")
    public User findByLogin(final String login) {
        Optional<User> byLogin = repository.findByLogin(login);
        return byLogin.orElse(null);
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @Override
    @PostFilter("@authorizerUserService.isPrincipalId(filterObject.id) or hasRole('ADMIN')")
    public List<User> findAll() {
        List<User> all = repository.findAll();
        return all;
    }

    /**
     * Create user.
     *
     * @param user the user
     * @return the user
     */
    @Override
    public User create(final User user) {
        if (repository.findByLogin(user.getLogin()).isPresent()) {
            throw new UserFound();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<UserRole> roles = Set.of(UserRole.OWNER);
        user.setUserRoles(roles);
        repository.save(user);
        return user;
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    @Override
    @PreAuthorize("hasAuthority('admin:delete')")
    public void delete(final UUID id) {
        repository.deleteById(id);
    }
}

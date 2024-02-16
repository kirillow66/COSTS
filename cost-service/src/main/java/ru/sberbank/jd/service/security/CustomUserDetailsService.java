package ru.sberbank.jd.service.security;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.entity.User;
import ru.sberbank.jd.entity.security.CustomUserDetailsEntity;
import ru.sberbank.jd.repository.UserRepository;

/**
 * The type Custom user details service.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository repository;

    /**
     * Sets user repository.
     *
     * @param repository the repository
     */
    @Autowired
    public void setUserRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byLogin = repository.findByLogin(username);
        return byLogin.map(CustomUserDetailsEntity::new)
                .orElseThrow(()->new UsernameNotFoundException("User not found"));
    }
}

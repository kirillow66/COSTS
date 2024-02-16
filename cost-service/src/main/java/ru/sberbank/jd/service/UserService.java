package ru.sberbank.jd.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import ru.sberbank.jd.entity.User;

/**
 * The interface User service.
 */
public interface UserService {
    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    User findById(UUID id);

    /**
     * Gets all.
     *
     * @return the all
     */
    List<User> findAll();

    /**
     * Find by login optional.
     *
     * @param login the login
     * @return the optional
     */
    User findByLogin(String login);

    /**
     * Create user.
     *
     * @param user the user
     * @return the user
     */
    User create(User user);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(UUID id);    
}

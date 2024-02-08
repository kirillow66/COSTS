package ru.sberbank.jd.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sberbank.jd.entity.User;

/**
 * The interface User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    /**
     * Find by login optional.
     *
     * @param login the login
     * @return the optional
     */
    Optional<User> findByLogin(String login);

}

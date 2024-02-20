package ru.sberbank.jd.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.jd.entity.Account;

/**
 * The interface Account repository.
 */
public interface AccountRepository extends JpaRepository<Account, UUID> {

}

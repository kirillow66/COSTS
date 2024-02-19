package ru.sberbank.jd.service;

import java.util.List;
import java.util.UUID;
import ru.sberbank.jd.dto.AccountDto;

/**
 * The interface Account service.
 */
public interface AccountService {

    /**
     * Gets all accounts.
     *
     * @return the all accounts
     */
    List<AccountDto> getAllAccounts();

    /**
     * Gets account by id.
     *
     * @param id the id
     * @return the account by id
     */
    AccountDto getAccountById(UUID id);

    /**
     * Create account account dto.
     *
     * @param accountDto the account dto
     * @return the account dto
     */
    AccountDto createAccount(AccountDto accountDto);

    /**
     * Update account account dto.
     *
     * @param id         the id
     * @param accountDto the account dto
     * @return the account dto
     */
    AccountDto updateAccount(UUID id, AccountDto accountDto);

    /**
     * Delete account string.
     *
     * @param id the id
     * @return the string
     */
    String deleteAccount(UUID id);
}


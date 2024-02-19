package ru.sberbank.jd.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.dto.AccountDto;
import ru.sberbank.jd.entity.Account;
import ru.sberbank.jd.repository.AccountRepository;

/**
 * The type Account service.
 */
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    /**
     * Instantiates a new Account service.
     *
     * @param accountRepository the account repository
     */
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<AccountDto> accountDto = accountRepository.findAll().stream()
                .map(account -> new AccountDto(account.getId(), account.getName()))
                .collect(Collectors.toList());
        return accountDto;
    }

    @Override
    public AccountDto getAccountById(UUID id) {
        AccountDto accountDto = accountRepository.findById(id)
                .map(account -> new AccountDto(account.getId(), account.getName()))
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + id));
        return accountDto;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = new Account();
        account.setName(accountDto.getName());
        account = accountRepository.save(account);
        accountDto.setId(account.getId());
        return accountDto;
    }

    @Override
    public AccountDto updateAccount(UUID id, AccountDto accountDto) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + id));

        account.setName(accountDto.getName());
        account = accountRepository.save(account);

        return new AccountDto(account.getId(), account.getName());
    }

    @Override
    public String deleteAccount(UUID id) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + id));
        accountRepository.delete(account);
        String massage = "Account with id = " + id + " has been deleted!";
        return massage;
    }
}

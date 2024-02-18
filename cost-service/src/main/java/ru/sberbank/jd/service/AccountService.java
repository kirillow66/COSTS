package ru.sberbank.jd.service;

import java.util.List;
import java.util.UUID;
import ru.sberbank.jd.dto.AccountDto;

public interface AccountService {

    List<AccountDto> getAllAccounts();

    AccountDto getAccountById(UUID id);

    AccountDto createAccount(AccountDto accountDto);

    AccountDto updateAccount(UUID id, AccountDto accountDto);

    String deleteAccount(UUID id);
}


package ru.sberbank.jd.controller;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.sberbank.jd.dto.AccountDto;
import ru.sberbank.jd.service.AccountService;

/**
 * The type Account controller.
 */
@Controller
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    /**
     * Instantiates a new Account controller.
     *
     * @param accountService the account service
     */
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Gets all accounts.
     *
     * @param model the model
     * @return the all accounts
     */
    @GetMapping
    public String getAllAccounts(Model model) {
        List<AccountDto> accountsDto = accountService.getAllAccounts();
        model.addAttribute("accounts", accountsDto);
        return "accounts/accounts";
    }

    /**
     * Gets account by id.
     *
     * @param id    the id
     * @param model the model
     * @return the account by id
     */
    @GetMapping("/{id}")
    public String getAccountById(@PathVariable("id") UUID id, Model model) {
        AccountDto accountDto = accountService.getAccountById(id);
        model.addAttribute("accountDto", accountDto);
        return "accounts/account_id";
    }

    /**
     * Show create account form string.
     *
     * @param accountDto the account dto
     * @return the string
     */
    @GetMapping("/create")
    public String showCreateAccountForm(@ModelAttribute("accountDto") AccountDto accountDto) {
        return "accounts/account_get_create";
    }

    /**
     * Create account string.
     *
     * @param accountDto the account dto
     * @return the string
     */
    @PostMapping("/create")
    public String createAccount(@ModelAttribute("accountDto") AccountDto accountDto) {
        accountService.createAccount(accountDto);
        return "redirect:/accounts";
    }

    /**
     * Show update account form string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/update/{id}")
    public String showUpdateAccountForm(@PathVariable("id") UUID id, Model model) {
        AccountDto accountDto = accountService.getAccountById(id);
        model.addAttribute("accountDto", accountDto);
        return "accounts/accounts_update_id";
    }

    /**
     * Update account string.
     *
     * @param id         the id
     * @param accountDto the account dto
     * @return the string
     */
    @PostMapping("/update/{id}")
    public String updateAccount(@PathVariable("id") UUID id, @ModelAttribute("accountDto") AccountDto accountDto) {
        accountService.updateAccount(id, accountDto);
        return "redirect:/accounts";
    }

    /**
     * Delete account string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable("id") UUID id, Model model) {
        String result = accountService.deleteAccount(id);
        model.addAttribute("message", result);
        return "accounts/account_delete";
    }

    /**
     * Handle entity not found exception string.
     *
     * @param ex    the ex
     * @param model the model
     * @return the string
     */
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEntityNotFoundException(EntityNotFoundException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }
}
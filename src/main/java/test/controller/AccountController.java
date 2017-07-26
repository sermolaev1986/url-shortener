package test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.domain.Account;
import test.dto.AccountRegistrationRequestDto;
import test.dto.AccountRegistrationResponseDto;
import test.service.AccountService;

import java.io.IOException;
import java.util.Locale;

/**
 * REST controller which exposes functionality to register new accounts.
 */
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private MessageSource messageSource;

    @PostMapping
    public AccountRegistrationResponseDto registerAccount(@RequestBody AccountRegistrationRequestDto accountDto, Locale locale) throws IOException {
        if (accountService.exists(accountDto.getAccountId())) {
            return new AccountRegistrationResponseDto(
                    false,
                    messageSource.getMessage("registration.account.exists", null, locale),
                    null);
        } else {
            Pair<Account, String> account = accountService.createAccount(accountDto.getAccountId());
            return new AccountRegistrationResponseDto(
                    true,
                    messageSource.getMessage("registration.successful", null, locale),
                    account.getSecond());
        }

    }
}

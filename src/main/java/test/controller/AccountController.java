package test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.domain.Account;
import test.dto.AccountRegistrationRequestDto;
import test.dto.AccountRegistrationResponseDto;
import test.repository.AccountRepository;
import test.service.AccountService;
import test.service.PasswordGenerationService;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @Autowired private AccountService accountService;

    @PostMapping
    public AccountRegistrationResponseDto registerAccount(@RequestBody AccountRegistrationRequestDto accountDto) throws IOException {
        //TODO message source
        if (accountService.exists(accountDto.getAccountId()))    {
            return new AccountRegistrationResponseDto(false, "Account already exists", null);
        } else {
            Pair<Account, String> account = accountService.createAccount(accountDto.getAccountId());
            return new AccountRegistrationResponseDto(true, "Your account is opened", account.getSecond());
        }

    }
}

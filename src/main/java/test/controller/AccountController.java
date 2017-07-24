package test.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.dto.AccountDto;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @PostMapping
    public void registerAccount(@RequestBody AccountDto account) throws IOException {

    }
}

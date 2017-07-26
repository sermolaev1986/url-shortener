package test.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.util.Pair;
import org.springframework.security.crypto.password.PasswordEncoder;
import test.domain.Account;
import test.repository.AccountRepository;

import static org.hamcrest.CoreMatchers.is;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService = new AccountService();

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private PasswordGenerationService passwordGenerationService;
    @Mock
    private AccountRepository accountRepository;

    @Test
    public void testCreateAccount() throws Exception {
        String encodedPassword = "fgert23fg544";
        String plainPassword = "gfgr44453fgd3";
        Account account = new Account("123", encodedPassword);

        Mockito.when(passwordGenerationService.generatePassword()).thenReturn(plainPassword);
        Mockito.when(passwordEncoder.encode(plainPassword)).thenReturn(encodedPassword);

        Pair<Account, String> expected = Pair.of(account, plainPassword);

        Pair<Account, String> actual = accountService.createAccount("123");
        Assert.assertThat(actual, is(expected));
        Mockito.verify(accountRepository).save(account);
    }

}
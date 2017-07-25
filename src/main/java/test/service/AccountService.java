package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import test.domain.Account;
import test.repository.AccountRepository;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordGenerationService passwordGenerationService;

    @Autowired
    private AccountRepository accountRepository;

    public boolean exists(String accountId) {
        return accountRepository.exists(accountId);
    }

    public Pair<Account, String> createAccount(String accountId) {
        String plainPassword = passwordGenerationService.generatePassword();
        Account account = new Account(accountId, passwordEncoder.encode(plainPassword));
        accountRepository.save(account);
        return Pair.of(account, plainPassword);
    }

    public String getCurrentAccountId() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
        if (!accountRepository.exists(accountId)) {
            throw new UsernameNotFoundException(accountId);
        }

        Account account = accountRepository.findById(accountId);
        return new User(account.getId(), account.getPassword(), AuthorityUtils.NO_AUTHORITIES);
    }
}

package test.repository;

import org.springframework.data.repository.Repository;
import test.domain.Account;

import javax.transaction.Transactional;

/**
 * Access layer for {@link Account} entity.
 */
@Transactional
public interface AccountRepository extends Repository<Account, String> {
    boolean exists(String id);
    Account save(Account account);
    Account findById(String id);
}

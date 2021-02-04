package kr.co.broadwave.homeservice.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Minkyu
 * Date : 2021-02-04
 * Time :
 * Remark : Account Service
 */
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(AccountRepository accountRepository,
                          PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveAccount(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        this.accountRepository.save(account);
    }

    public Optional<Account> findByUserid(String userid) {
        return this.accountRepository.findByUserid(userid);
    }

}
package kr.co.broadwave.homeservice.configs;

import kr.co.broadwave.homeservice.admin.Account;
import kr.co.broadwave.homeservice.admin.AccountRole;
import kr.co.broadwave.homeservice.admin.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Minkyu
 * Date : 2021-02-04
 * Time :
 * Remark : 디비내에 비번이없으면 자동으로 만들어주기 admin/1
 */
@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    AccountService accountService;

    @Override
    public void run(ApplicationArguments args) {

        //사용자저장
        Account account = Account.builder()
                .userid("admin")
                .password("1")
                .insertDateTime(LocalDateTime.now())
                .role(AccountRole.ROLE_ADMIN)
                .build();
        if(!accountService.findByUserid(account.getUserid()).isPresent()){
            accountService.saveAccount(account);
        }

    }

}

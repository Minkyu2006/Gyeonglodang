package kr.co.broadwave.homeservice.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author Minkyu
 * Date : 2021-02-04
 * Time :
 * Remark : 사용자정보 레파지토리
 */
public interface AccountRepository extends JpaRepository<Account,Long>,QuerydslPredicateExecutor<Account> {
    @Query("select a from Account a where a.userid = :userid")
    Optional<Account> findByUserid(@Param("userid") String userid);

    @Query("select a from Account a where a.userid = :userid")
    Optional<Account> findByUseridAndApprovalType(@Param("userid") String userid);

}

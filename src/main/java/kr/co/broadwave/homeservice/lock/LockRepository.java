package kr.co.broadwave.homeservice.lock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LockRepository extends JpaRepository<Lock,Long>, QuerydslPredicateExecutor<Lock> {

    @Query("select a from Lock a where a.id = :id")
    Optional<Lock> findById(@Param("id") String id);

}

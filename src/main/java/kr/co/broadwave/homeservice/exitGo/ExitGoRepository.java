package kr.co.broadwave.homeservice.exitGo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ExitGoRepository extends JpaRepository<ExitGo,Long>, QuerydslPredicateExecutor<ExitGo> {

    @Query("select a from ExitGo a where a.id = :id")
    Optional<ExitGo> findByIdLeave(@Param("id") String id);

}

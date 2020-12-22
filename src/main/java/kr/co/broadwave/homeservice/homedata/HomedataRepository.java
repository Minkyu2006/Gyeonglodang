package kr.co.broadwave.homeservice.homedata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HomedataRepository extends JpaRepository<HomeData,Long>, QuerydslPredicateExecutor<HomeData> {

    @Query("select a from HomeData a where a.s_type = :s_type")
    Optional<HomeData> findByIdData(@Param("s_type") String s_type);

}

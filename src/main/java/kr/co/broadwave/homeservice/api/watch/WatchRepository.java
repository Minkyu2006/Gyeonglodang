package kr.co.broadwave.homeservice.api.watch;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author InSeok
 * Date : 2021-01-04
 * Remark :
 */
public interface WatchRepository extends JpaRepository<Watch,String> {
}

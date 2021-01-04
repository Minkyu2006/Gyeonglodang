package kr.co.broadwave.homeservice.api.watch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author InSeok
 * Date : 2021-01-04
 * Remark :
 */
@Service
@Slf4j
public class WatchService {
    private final WatchRepository watchRepository;

    @Autowired
    public WatchService(WatchRepository watchRepository) {
        this.watchRepository = watchRepository;
    }
    public void save(WatchMapper watchMapper){
        log.info("스마트워치 재실정보 저장 : '" + watchMapper.toString() +"'");
        watchRepository.save(watchMapper.convertWatch());

    }
}

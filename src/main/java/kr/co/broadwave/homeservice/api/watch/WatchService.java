package kr.co.broadwave.homeservice.api.watch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author InSeok
 * Date : 2021-01-04
 * Remark :
 */
@Service
@Slf4j
public class WatchService {
    private final WatchRepository watchRepository;

    private static final int REPEAT = 3;
    private final Map<ResponseBodyEmitter, AtomicInteger> emitterCountMap = new HashMap<>();

    @Autowired
    public WatchService(WatchRepository watchRepository) {
        this.watchRepository = watchRepository;
    }
    public void save(WatchMapper watchMapper){
        log.info("스마트워치 재실정보 저장 : '" + watchMapper.toString() +"'");
        watchRepository.save(watchMapper.convertWatch());

    }

    public void add(ResponseBodyEmitter emitter) {
        emitterCountMap.put(emitter, new AtomicInteger(0));
    }

    @Scheduled(fixedRate = 1000L)
    public void emit() {

        List<ResponseBodyEmitter> toBeRemoved = new ArrayList<>(emitterCountMap.size());

        for (Map.Entry<ResponseBodyEmitter, AtomicInteger> entry : emitterCountMap.entrySet()) {

            Integer count = entry.getValue().incrementAndGet();
            Watch watch = Watch.builder()
                    .id(String.valueOf(count))
                    .name("홍길동" + String.valueOf(count))
                    .modify_dt(LocalDateTime.now())
                    .build();
            ResponseBodyEmitter emitter = entry.getKey();
            try {
                emitter.send(watch);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                toBeRemoved.add(emitter);
            }

            if (count >= REPEAT) {
                toBeRemoved.add(emitter);
            }
        }

        for (ResponseBodyEmitter emitter : toBeRemoved) {
            emitterCountMap.remove(emitter);
        }
    }
}

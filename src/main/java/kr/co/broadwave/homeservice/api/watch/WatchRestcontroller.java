package kr.co.broadwave.homeservice.api.watch;

import kr.co.broadwave.homeservice.common.AjaxResponse;
import kr.co.broadwave.homeservice.common.ResponseErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author InSeok
 * Date : 2021-01-04
 * Remark : 애플워치 재실 등록 API서버 (api/v1/watch)
 */
@RestController
@RequestMapping("api/v1/watch")
@Slf4j
public class WatchRestcontroller {

    private final WatchService watchService;

    @Autowired
    public WatchRestcontroller(WatchService watchService) {
        this.watchService = watchService;
    }

    @PostMapping
    public ResponseEntity<Map<String,Object>> watchReg(@RequestBody WatchMapper watchMapper){
        AjaxResponse res = new AjaxResponse();
        if ( watchMapper.getId() == null || watchMapper.getId().trim().isEmpty()
            || watchMapper.getName() == null || watchMapper.getName().trim().isEmpty()
        ){
            return ResponseEntity.ok(res.fail(ResponseErrorCode.E001.getCode(),ResponseErrorCode.E001.getDesc()));
        }
        watchService.save(watchMapper);
        return ResponseEntity.ok(res.success());
    }
}

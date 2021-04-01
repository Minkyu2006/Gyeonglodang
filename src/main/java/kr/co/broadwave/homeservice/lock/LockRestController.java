package kr.co.broadwave.homeservice.lock;

import kr.co.broadwave.homeservice.common.AjaxResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author 민규
 * Date : 2020-04-01
 * Time :
 * Remark : 도어락잠김 / 해제 액티브
 */
@Slf4j
@RestController
@RequestMapping("/lock")
public class LockRestController {

    private final LockService lockService;

    @Autowired
    public LockRestController(LockService lockService) {
        this.lockService = lockService;
    }

    @PostMapping("stateGet")
    public ResponseEntity<Map<String,Object>> stateGet() {
        AjaxResponse res = new AjaxResponse();
        HashMap<String, Object> data = new HashMap<>();

        log.info("도어락잠김 상태 가져오기");

        Lock lockState = new Lock();
        Optional<Lock> lock = lockService.findById("pkvalue");
        if(!lock.isPresent()) {
            lockState.setId("pkvalue");
            lockState.setState("Y");
            lockService.save(lockState);
            data.put("lockState","Y");
        }else{
            if(lock.get().getState().equals("Y")){
                data.put("lockState","Y");
            }else{
                data.put("lockState","N");
            }
        }
        res.addResponse("data",data);
        return ResponseEntity.ok(res.success());
    }

    @PostMapping("stateUpdate")
    public ResponseEntity<Map<String,Object>> stateUpdate() {
        AjaxResponse res = new AjaxResponse();
        HashMap<String, Object> data = new HashMap<>();

        log.info("도어락잠김 버튼 클릭");

        Lock lockState = new Lock();
        Optional<Lock> lock = lockService.findById("pkvalue");
     
        if(!lock.isPresent()) {
            lockState.setId("pkvalue");
            lockState.setState("Y");
            data.put("lockState","Y");
            lockService.save(lockState);
        }else{
            lockState.setId("pkvalue");
            if(lock.get().getState().equals("Y")){
                lockState.setState("N");
                data.put("lockState","N");
            }else{
                lockState.setState("Y");
                data.put("lockState","Y");
            }
            lockService.save(lockState);
        }

        res.addResponse("data",data);
        return ResponseEntity.ok(res.success());
    }

}

package kr.co.broadwave.homeservice.api.system;

import kr.co.broadwave.homeservice.common.AjaxResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/apiData")
public class SystemRestController {

    private final SystemService systemService;

    @Autowired
    public SystemRestController(SystemService systemService) {
        this.systemService = systemService;
    }

    // 운영정책 데이터 가져오기
    @PostMapping("operationGet")
    public ResponseEntity<Map<String,Object>> operationGet(@RequestParam(value="value", defaultValue="") String value){
        AjaxResponse res = new AjaxResponse();
        HashMap<String, Object> data = new HashMap<>();
//        log.info("value : "+value);
        data.put("operationDate",systemService.callURL(value));
        res.addResponse("data",data);
        return ResponseEntity.ok(res.success());
    }

}

package kr.co.broadwave.homeservice.exitGo;

import kr.co.broadwave.homeservice.common.AjaxResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

/**
 * @author 민규
 * Date : 2020-12-24
 * Time :
 * Remark : 퇴실버튼
 */
@Slf4j
@RestController
@RequestMapping("/exitGo")
public class ExitGoRestController {

    private final ExitGoService exitGoService;

    @Autowired
    public ExitGoRestController(ExitGoService exitGoService) {
        this.exitGoService = exitGoService;
    }

    @PostMapping("outGo")
    public ResponseEntity<Map<String,Object>> outGo() {
        AjaxResponse res = new AjaxResponse();

        log.info("퇴실버튼 클릭");

        // 현재 데이터 받아오기
        ExitGo exitGoupdate = new ExitGo();
        Optional<ExitGo> exitGo = exitGoService.findByIdLeave("pkvalue");
        if(!exitGo.isPresent()) {
            log.info("exitGo 받아오기 실패");
        }else{
            String leaveRequset = exitGo.get().getLeaveRequest();
            LocalDateTime modify_dt = exitGo.get().getModify_dt();
            log.info("leaveRequset : "+leaveRequset);
            log.info("modify_dt : "+modify_dt);

            // 이제 업데이트 하기
            exitGoupdate.setId("pkvalue");
            exitGoupdate.setLeaveRequest("Y");
            exitGoupdate.setPreLeaveRequest(leaveRequset);
            exitGoupdate.setModify_dt(LocalDateTime.now());
            exitGoupdate.setPre_modify_dt(modify_dt);
            exitGoService.save(exitGoupdate);
        }

        return ResponseEntity.ok(res.success());
    }

}

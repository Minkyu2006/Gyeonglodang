package kr.co.broadwave.homeservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 민규
 * Date : 2021-01-05
 * Time :
 * Remark : 관리자용 컨트롤러
 */
@Slf4j
@Controller
@RequestMapping("/admindashboard")
public class AdminController {

    // 대시보드 메인화면(관리자용)
    @RequestMapping("")
    public String admin_main(){
        return "index-admin";
    }

//    // 시스템환경설정
//    @RequestMapping("/system")
//    public String system(){
//        return "adminstate/system";
//    }

    // 관리자 - 온도페이지
    @RequestMapping("/temp")
    public String admintemp(){
        return "adminstate/temp";
    }

    // 관리자 - 습도페이지
    @RequestMapping("/humidity")
    public String adminhumidity(){
        return "adminstate/humidity";
    }

    // 관리자 - 공기질페이지
    @RequestMapping("/air")
    public String adminair(){
        return "adminstate/air";
    }

    // 관리자 - 거실페이지
    @RequestMapping("/living")
    public String living(){
        return "location/living";
    }

    // 관리자 - 주방페이지
    @RequestMapping("/kitchen")
    public String kitchen(){
        return "location/kitchen";
    }

    // 관리자 - 안방페이지
    @RequestMapping("/inner")
    public String inner(){
        return "location/inner";
    }

    // 관리자 - 운동페이지
    @RequestMapping("/exercise")
    public String exercise(){
        return "location/exercise";
    }

    // 관리자 - 창고페이지
    @RequestMapping("/warehouse")
    public String warehouse(){
        return "location/warehouse";
    }

    // 관리자 - 배터리페이지
    @RequestMapping("/battery")
    public String battery(){
        return "location/battery";
    }

}

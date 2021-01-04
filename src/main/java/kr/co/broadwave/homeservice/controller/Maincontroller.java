package kr.co.broadwave.homeservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 민규
 * Date : 2020-12-22
 * Time : 11:31
 * Remark : 메인 컨트롤러
 */
@Slf4j
@Controller
public class Maincontroller {

    // 대시보드 메인화면(사용자용)
    @RequestMapping("/")
    public String main(){
        return "index";
    }

    // 사용자 - 온도페이지
    @RequestMapping("/usertemp")
    public String usertemp(){
        return "state/usertemp";
    }

    // 사용자 - 습도페이지
    @RequestMapping("/userhumidity")
    public String userhumidity(){
        return "state/userhumidity";
    }

    // 사용자 - 공기질페이지
    @RequestMapping("/userair")
    public String userair(){
        return "state/userair";
    }



    // 대시보드 메인화면(관리자용)
    @RequestMapping("/admin")
    public String admin_main(){
        return "index-admin";
    }

    // 관리자 - 온도페이지
    @RequestMapping("/temp")
    public String admintemp(){
        return "state/temp";
    }

    // 관리자 - 습도페이지
    @RequestMapping("/humidity")
    public String adminhumidity(){
        return "state/humidity";
    }

    // 관리자 - 공기질페이지
    @RequestMapping("/air")
    public String adminair(){
        return "state/air";
    }



    // 온도상세보기(그래프)
    @RequestMapping("/tempDetail")
    public String temp_detail(){
        return "state/temp_detail";
    }


}

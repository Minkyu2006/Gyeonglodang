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
public class MainController {

    // 대시보드 메인화면(사용자용)
    @RequestMapping("/")
    public String main() {
        return "index";
    }

    @RequestMapping("/indextest")
    public String indextest() {
        return "indextest";
    }

    // 사용자 - 온도페이지
    @RequestMapping("/usertemp")
    public String usertemp() {
        return "userstate/usertemp";
    }

    // 사용자 - 습도페이지
    @RequestMapping("/userhumidity")
    public String userhumidity() {
        return "userstate/userhumidity";
    }

    // 사용자 - 공기질페이지
    @RequestMapping("/userair")
    public String userair() {
        return "userstate/userair";
    }

}
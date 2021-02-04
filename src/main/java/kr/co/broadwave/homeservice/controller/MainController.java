package kr.co.broadwave.homeservice.controller;

import kr.co.broadwave.homeservice.admin.Account;
import kr.co.broadwave.homeservice.admin.AccountService;
import kr.co.broadwave.homeservice.common.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * @author 민규
 * Date : 2021-01-05
 * Time :
 * Remark : 관리자용 컨트롤러
 */
@Slf4j
@Controller
public class MainController {
    private final AccountService accountService;

    @Autowired
    public MainController(AccountService accountService) {
        this.accountService = accountService;
    }

    // 스마트경로당 시작 빈페이지
    @RequestMapping("/")
    public String start() {
        return "start";
    }

    // 로그인페이지(관리자용페이지 진입시)
    @RequestMapping("/login")
    public String login(HttpServletRequest request){
        String referrer = request.getHeader("Referer");
        request.getSession().setAttribute("prevPage", referrer);
        return "login";
    }

    // 대시보드 메인화면(사용자용)
    @RequestMapping("/localdashboard")
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
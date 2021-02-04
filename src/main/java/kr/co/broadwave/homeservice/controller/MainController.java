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

    // 시스템환경설정
    @RequestMapping("/system")
    public String system(){
        return "adminstate/system";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request){
        String referrer = request.getHeader("Referer");
        request.getSession().setAttribute("prevPage", referrer);
        return "login";
    }

    @RequestMapping("/loginsuccess")
    public String loginsuccess(HttpServletRequest request){
        HttpSession session = request.getSession();
        String login_ip = CommonUtils.getIp(request);
        Optional<Account> optionalAccount = accountService.findByUserid(request.getUserPrincipal().getName());
        if (optionalAccount.isPresent()) {
            log.info("============Login Success============");
            log.info(" 로그인 IP " + login_ip);
            log.info(" session userid " + session.getAttribute("userid"));
            log.info(" session role " + session.getAttribute("role"));
            log.info("=====================================");
        }
        return "index-admin";
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
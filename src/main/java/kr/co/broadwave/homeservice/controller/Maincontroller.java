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

    //메인화면
    @RequestMapping("/")
    public String main(){
        return "index";
    }

}

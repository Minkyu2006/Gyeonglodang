package kr.co.broadwave.homeservice.controller;

import kr.co.broadwave.homeservice.mqttsetting.MyMqttClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    // 대시보드 메인화면(관리자용)
    @RequestMapping("/admin")
    public String admin_main(){
        return "index-admin";
    }

    // 온도페이지
    @RequestMapping("/temp")
    public String temp(){
        return "state/temp";
    }

    // 습도페이지
    @RequestMapping("/humidity")
    public String humidity(){
        return "state/humidity";
    }

    // 공기질페이지
    @RequestMapping("/air")
    public String air(){
        return "state/air";
    }

    // 온도상세보기(그래프)
    @RequestMapping("/tempDetail")
    public String temp_detail(){
        return "state/temp_detail";
    }


}

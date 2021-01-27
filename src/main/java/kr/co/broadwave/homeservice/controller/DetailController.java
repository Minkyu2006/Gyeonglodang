package kr.co.broadwave.homeservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 민규
 * Date : 2021-01-05
 * Time :
 * Remark : 관리자용 컨트롤러
 */
@Slf4j
@Controller
public class DetailController {

    // 관리자전용 - 온도 상세보기(그래프)
    @RequestMapping("/temp/tempdetail/{room}")
    public String tempdetail(Model model, @PathVariable String room){
        model.addAttribute("room", room);
        return "detail/tempdetail";
    }

    // 관리자전용 - 습도 상세보기(그래프)
    @RequestMapping("/humidity/humdetail/{room}")
    public String humdetail(Model model, @PathVariable String room){
        model.addAttribute("room", room);
        return "detail/humdetail";
    }

    // 관리자전용 - 공기질 상세보기(그래프)
    @RequestMapping("/air/airdetail/{room}")
    public String airdetail(Model model, @PathVariable String room){
        model.addAttribute("room", room);
        return "detail/airdetail";
    }

}

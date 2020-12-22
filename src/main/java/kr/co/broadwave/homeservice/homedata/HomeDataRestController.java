package kr.co.broadwave.homeservice.homedata;

import kr.co.broadwave.homeservice.common.AjaxResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author 민규
 * Date : 2020-12-22
 * Time : 11:31
 * Remark : 테이블 데이터받기용
 */
@Slf4j
@RestController
@RequestMapping("/homedata")
public class HomeDataRestController {

    private final HomeDataService homedataRepository;

    @Autowired
    public HomeDataRestController(HomeDataService homedataRepository) {
        this.homedataRepository = homedataRepository;
    }

    @PostMapping("dataInfo")
    public ResponseEntity<Map<String,Object>> homedata(){

        AjaxResponse res = new AjaxResponse();
        HashMap<String, Object> data = new HashMap<>();

        HomeData sensorData;
        Optional<HomeData> light = homedataRepository.findByIdData("light");
        if(!light.isPresent()) {
            log.info("light 받아오기 실패");
        }else{
            sensorData = light.get();
            log.info(""+sensorData);
            data.put("lightData",sensorData);
        }

        Optional<HomeData> temperature = homedataRepository.findByIdData("temperature");
        if(!temperature.isPresent()) {
            log.info("temperature 받아오기 실패");
        }else{
            sensorData = temperature.get();
            log.info(""+sensorData);
            data.put("temperatureData",sensorData);
        }

        res.addResponse("data",data);

        return ResponseEntity.ok(res.success());

    }


}

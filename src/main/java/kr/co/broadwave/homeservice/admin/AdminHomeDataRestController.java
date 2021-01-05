package kr.co.broadwave.homeservice.admin;

import kr.co.broadwave.homeservice.common.AjaxResponse;
import kr.co.broadwave.homeservice.homedata.HomeData;
import kr.co.broadwave.homeservice.homedata.HomeDataService;
import kr.co.broadwave.homeservice.mqttsetting.MyMqttClient;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * @author 민규
 * Date : 2021-01-05
 * Time :]
 * Remark : 관리자용 데이터받기용
 */
@Slf4j
@RestController
@RequestMapping("/adminhomedata")
public class AdminHomeDataRestController {

    private final HomeDataService homeDataService;

    @Autowired
    public AdminHomeDataRestController(HomeDataService homeDataService) {
        this.homeDataService = homeDataService;
    }

    // 관리자페이지 전용(모든 온도나타내기)
    @PostMapping("adminTempDataInfo")
    public ResponseEntity<Map<String,Object>> adminTempDataInfo(){

        AjaxResponse res = new AjaxResponse();
        HashMap<String, Object> data = new HashMap<>();

        HomeData sensorData;

        Optional<HomeData> temperature = homeDataService.findByIdData("temperature");
        if(!temperature.isPresent()) {
            log.info("temperature 받아오기 실패");
        }else{
            sensorData = temperature.get();
            log.info("온도 데이터 : "+sensorData);
            data.put("temperatureData",sensorData);
        }

        res.addResponse("data",data);
        return ResponseEntity.ok(res.success());
    }

    // 관리자페이지 전용(모든 습도나타내기)
    @PostMapping("adminhumDataInfo")
    public ResponseEntity<Map<String,Object>> adminhumDataInfo(){

        AjaxResponse res = new AjaxResponse();
        HashMap<String, Object> data = new HashMap<>();

        HomeData sensorData;

        Optional<HomeData> humidity = homeDataService.findByIdData("humidity");
        if(!humidity.isPresent()) {
            log.info("humidity 받아오기 실패");
        }else{
            sensorData = humidity.get();
            log.info("온도 데이터 : "+sensorData);
            data.put("humidityData",sensorData);
        }

        res.addResponse("data",data);
        return ResponseEntity.ok(res.success());
    }

    // 관리자페이지 전용(모든 배터리가져오기)
    @PostMapping("adminBatteryDataInfo")
    public ResponseEntity<Map<String,Object>> adminBatteryDataInfo(){

        AjaxResponse res = new AjaxResponse();
        HashMap<String, Object> data = new HashMap<>();

        HomeData sensorData;

        Optional<HomeData> battery = homeDataService.findByIdData("battery");
        if(!battery.isPresent()) {
            log.info("batteryData 받아오기 실패");
        }else{
            sensorData = battery.get();
            log.info("온도 데이터 : "+sensorData);
            data.put("batteryData",sensorData);
        }

        res.addResponse("data",data);
        return ResponseEntity.ok(res.success());
    }

}

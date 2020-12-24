package kr.co.broadwave.homeservice.homedata;

import kr.co.broadwave.homeservice.common.AjaxResponse;
import kr.co.broadwave.homeservice.mqttsetting.MyMqttClient;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Value("${broadwave.ha.iot.username}")
    private String BROADWAVE_USERNAME;
    @Value("${broadwave.ha.iot.password}")
    private String BROADWAVE_PASSWORD;
    @Value("${broadwave.ha.iot.url}")
    private String BROADWAVE_URL;

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
            log.info("조명 데이터 : "+sensorData);
            data.put("lightData",sensorData);
        }

        Optional<HomeData> temperature = homedataRepository.findByIdData("temperature");
        if(!temperature.isPresent()) {
            log.info("temperature 받아오기 실패");
        }else{
            sensorData = temperature.get();
            log.info("온도 데이터 : "+sensorData);
            data.put("temperatureData",sensorData);
        }

        Optional<HomeData> humidity = homedataRepository.findByIdData("humidity");
        if(!humidity.isPresent()) {
            log.info("humidity 받아오기 실패");
        }else{
            sensorData = humidity.get();
            log.info("습도 데이터 : "+sensorData);
            data.put("humidity",sensorData);
        }

        Optional<HomeData> airpurification = homedataRepository.findByIdData("airpurification");
        if(!airpurification.isPresent()) {
            log.info("airpurification 받아오기 실패");
        }else{
            sensorData = airpurification.get();
            log.info("공기청정기 데이터 : "+sensorData);
            data.put("airpurification",sensorData);
        }

        res.addResponse("data",data);

        return ResponseEntity.ok(res.success());

    }

    @PostMapping("lightOnOff")
    public ResponseEntity<Map<String,Object>> lightOnOff(@RequestParam(value="value", defaultValue="") String value) throws MqttException {
        AjaxResponse res = new AjaxResponse();
        MyMqttClient client = new MyMqttClient();
        client.init(BROADWAVE_USERNAME, BROADWAVE_PASSWORD, BROADWAVE_URL, "command");
        log.info("조명 명령하기 : "+value);
//        log.info("BROADWAVE_USERNAME : "+BROADWAVE_USERNAME);
//        log.info("BROADWAVE_PASSWORD : "+BROADWAVE_PASSWORD);
//        log.info("BROADWAVE_URL : "+BROADWAVE_URL);
        if(value.equals("ON")){
            client.sender("command/smart/lighton","{\"Dashboard\":\"lightONCommand\"}");
        }else{
            client.sender("command/smart/lightoff","{\"Dashboard\":\"lightOFFCommand\"}");
        }

        return ResponseEntity.ok(res.success());
    }

    @PostMapping("aqOnOff")
    public ResponseEntity<Map<String,Object>> aqOnOff(@RequestParam(value="value", defaultValue="") String value) throws MqttException {
        AjaxResponse res = new AjaxResponse();
        MyMqttClient client = new MyMqttClient();
        log.info("공기청정기 명령하기 : "+value);
        client.init(BROADWAVE_USERNAME, BROADWAVE_PASSWORD, BROADWAVE_URL, "command");
        if(value.equals("ON")){
            client.sender("command/smart/aqon","{\"Dashboard\":\"aqONCommand\"}");
        }else{
            client.sender("command/smart/aqoff","{\"Dashboard\":\"aqOFFCommand\"}");
        }

        return ResponseEntity.ok(res.success());
    }

    @PostMapping("opendoor")
    public ResponseEntity<Map<String,Object>> opendoor(@RequestParam(value="value", defaultValue="") String value) throws MqttException {
        AjaxResponse res = new AjaxResponse();
        MyMqttClient client = new MyMqttClient();
        log.info("문열기 명령하기 : "+value);
        client.init(BROADWAVE_USERNAME, BROADWAVE_PASSWORD, BROADWAVE_URL, "command");
        if(value.equals("OPEN")){
            client.sender("command/door1/unlock","{\"Dashboard\":\"openDoorCommand\"}");
        }
        return ResponseEntity.ok(res.success());
    }

}

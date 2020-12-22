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

    @PostMapping("lightOnOff")
    public ResponseEntity<Map<String,Object>> lightOnOff(@RequestParam(value="value", defaultValue="") String value) throws MqttException {
        AjaxResponse res = new AjaxResponse();
        MyMqttClient client = new MyMqttClient();
        log.info("조명 명령하기 : "+value);
//        log.info("BROADWAVE_USERNAME : "+BROADWAVE_USERNAME);
//        log.info("BROADWAVE_PASSWORD : "+BROADWAVE_PASSWORD);
//        log.info("BROADWAVE_URL : "+BROADWAVE_URL);
        if(value.equals("ON")){
            client.init(BROADWAVE_USERNAME, BROADWAVE_PASSWORD, BROADWAVE_URL, "command").sender("command/smart/lighton","{\"Dashboard\":\"lightONcommand\"}");
        }else{
            client.init(BROADWAVE_USERNAME, BROADWAVE_PASSWORD, BROADWAVE_URL, "command").sender("command/smart/lightoff","{\"Dashboard\":\"lightOFFcommand\"}");
        }

        return ResponseEntity.ok(res.success());
    }

    @PostMapping("aqOnOff")
    public ResponseEntity<Map<String,Object>> aqOnOff(@RequestParam(value="value", defaultValue="") String value) throws MqttException {
        AjaxResponse res = new AjaxResponse();
        MyMqttClient client = new MyMqttClient();
        log.info("공기청정기 명령하기 : "+value);

        if(value.equals("ON")){
            client.init(BROADWAVE_USERNAME, BROADWAVE_PASSWORD, BROADWAVE_URL, "command").sender("command/smart/aqon","{\"Dashboard\":\"aqONcommand\"}");
        }else{
            client.init(BROADWAVE_USERNAME, BROADWAVE_PASSWORD, BROADWAVE_URL, "command").sender("command/smart/aqoff","{\"Dashboard\":\"aqOFFcommand\"}");
        }

        return ResponseEntity.ok(res.success());
    }

}

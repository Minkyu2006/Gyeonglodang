package kr.co.broadwave.homeservice.chart;

import kr.co.broadwave.homeservice.common.AjaxResponse;
import kr.co.broadwave.homeservice.homedata.HomeData;
import kr.co.broadwave.homeservice.homedata.HomeDataService;
import kr.co.broadwave.homeservice.mqttsetting.MyMqttClient;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author 민규
 * Date : 2020-12-28
 * Time :
 * Remark : 센서데이터 받고 차트그리기
 */
@Slf4j
@RestController
@RequestMapping("/chart")
public class ChartRestController {

    private final ChartService chartService;

    @Autowired
    public ChartRestController(ChartService chartService){
        this.chartService = chartService;
    }


    @PostMapping("chartPost")
    public ResponseEntity<Map<String,Object>> chartPost() {
        AjaxResponse res = new AjaxResponse();

        return ResponseEntity.ok(res.success());
    }

    @PostMapping("temphistory")
    public ResponseEntity<Map<String,Object>> temphistory() {
        AjaxResponse res = new AjaxResponse();
        HashMap<String, Object> data = new HashMap<>();

        HashMap<String, Object> resData = chartService.getHistory("temp");

        log.info("data : "+resData.get("data"));

        data.put("datarow",resData.get("data"));
        res.addResponse("data",data);
        return ResponseEntity.ok(res.success());
    }

    @PostMapping("humhistory")
    public ResponseEntity<Map<String,Object>> humhistory() {
        AjaxResponse res = new AjaxResponse();
        HashMap<String, Object> data = new HashMap<>();

        HashMap<String, Object> resData = chartService.getHistory("hum");

        log.info("data : "+resData.get("data"));

        data.put("datarow",resData.get("data"));
        res.addResponse("data",data);
        return ResponseEntity.ok(res.success());
    }

    @PostMapping("airhistory")
    public ResponseEntity<Map<String,Object>> airhistory() {
        AjaxResponse res = new AjaxResponse();
        HashMap<String, Object> data = new HashMap<>();

        HashMap<String, Object> resData = chartService.getHistory("air");

        log.info("data : "+resData.get("data"));

        data.put("datarow",resData.get("data"));
        res.addResponse("data",data);
        return ResponseEntity.ok(res.success());
    }

}

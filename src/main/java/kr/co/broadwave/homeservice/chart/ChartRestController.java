package kr.co.broadwave.homeservice.chart;

import kr.co.broadwave.homeservice.common.AjaxResponse;
import kr.co.broadwave.homeservice.homedata.HomeData;
import kr.co.broadwave.homeservice.homedata.HomeDataService;
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
 * Date : 2020-12-28
 * Time :
 * Remark : 센서데이터 받고 차트그리기
 */
@Slf4j
@RestController
@RequestMapping("/chart")
public class ChartRestController {

    @PostMapping("chartPost")
    public ResponseEntity<Map<String,Object>> chartPost() {
        AjaxResponse res = new AjaxResponse();

        return ResponseEntity.ok(res.success());
    }

}

package kr.co.broadwave.homeservice.chart;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.broadwave.homeservice.common.AjaxResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 민규
 * Date : 2020-12-28
 * Time :
 * Remark : 센서데이터 받고 차트그리기
 */
@Service
@Slf4j
public class ChartService {

    private final ObjectMapper objectMapper;

    @Autowired
    public ChartService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private HashMap getHashMap(ResponseEntity<String> res) {
        if (res.getStatusCode() == HttpStatus.OK) {
            String bodystr = res.getBody();
            try{
                HashMap resultMap = objectMapper.readValue(bodystr, HashMap.class);
                return resultMap;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    // 온도의 history 가져오기
    public HashMap getHistory(String sensor){

        String url;
        if(sensor.equals("temp")){
            url = "https://elj7fafay2.execute-api.ap-northeast-2.amazonaws.com/SensorData/jb/v1/sensordata/temperature?intervalhour=12";
        }else{
            url = "https://elj7fafay2.execute-api.ap-northeast-2.amazonaws.com/SensorData/jb/v1/sensordata/humidity?intervalhour=12" ;
        }

        RestTemplate restTemplate = new RestTemplate();

        //header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("x-api-key","6A8Fa8HA29aZT65rCAnWx8O35zW9PVjA4dP1NZcE");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        //params
        Map<String, String> params = new HashMap<>();

        URI uri = UriComponentsBuilder
                .fromUriString(url)
                .buildAndExpand(params)
                .toUri();

        //queryParams
        uri = UriComponentsBuilder
                .fromUri(uri)
                .build()
                .toUri();


        ResponseEntity<String> res = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

        return getHashMap(res);
    }

}

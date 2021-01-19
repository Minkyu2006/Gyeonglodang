package kr.co.broadwave.homeservice.homedata;

import kr.co.broadwave.homeservice.common.AjaxResponse;
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

    @Value("${broadwave.ha.iot.clientname}")
    private String BROADWAVE_CLIENTID;

    private final HomeDataService homeDataService;

    @Autowired
    public HomeDataRestController(HomeDataService homeDataService) {
        this.homeDataService = homeDataService;
    }

    @PostMapping("dataInfo")
    public ResponseEntity<Map<String,Object>> homedata(){

        AjaxResponse res = new AjaxResponse();
        HashMap<String, Object> data = new HashMap<>();

        HomeData sensorData;
        Optional<HomeData> light = homeDataService.findByIdData("light");
        if(!light.isPresent()) {
            log.info("light 받아오기 실패");
            data.put("lightData","");
        }else{
            sensorData = light.get();
            log.info("조명 데이터 : "+sensorData);
            data.put("lightData",sensorData);
        }

        Optional<HomeData> temperature = homeDataService.findByIdData("temperature");
        if(!temperature.isPresent()) {
            log.info("temperature 받아오기 실패");
            data.put("temperatureData","");
        }else{
            sensorData = temperature.get();
            log.info("온도 데이터 : "+sensorData);
            data.put("temperatureData",sensorData);
        }

        Optional<HomeData> humidity = homeDataService.findByIdData("humidity");
        if(!humidity.isPresent()) {
            log.info("humidity 받아오기 실패");
            data.put("humidity","");
        }else{
            sensorData = humidity.get();
            log.info("습도 데이터 : "+sensorData);
            data.put("humidity",sensorData);
        }

        Optional<HomeData> airpurification = homeDataService.findByIdData("airpurification");
        if(!airpurification.isPresent()) {
            log.info("airpurification 받아오기 실패");
            data.put("airpurification","");
        }else{
            sensorData = airpurification.get();
            log.info("공기청정기 데이터 : "+sensorData);
            data.put("airpurification",sensorData);
        }

        Optional<HomeData> weather = homeDataService.findByIdData("weather");
        if(!weather.isPresent()) {
            log.info("weather 받아오기 실패");
            data.put("weather","");
        }else{
            sensorData = weather.get();
            log.info("날씨 데이터 : "+sensorData);
            data.put("weather",sensorData);
        }

        Optional<HomeData> aircondition = homeDataService.findByIdData("aircondition");
        if(!aircondition.isPresent()) {
            log.info("aircondition 받아오기 실패");
            data.put("aircondition","");
        }else{
            sensorData = aircondition.get();
            log.info("날씨 데이터 : "+sensorData);
            data.put("aircondition",sensorData);
        }

        Optional<HomeData> doorsensor = homeDataService.findByIdData("doorsensor");
        if(!doorsensor.isPresent()) {
            log.info("doorsensor 받아오기 실패");
            data.put("doorsensor","");
        }else{
            sensorData = doorsensor.get();
            log.info("문센서 데이터 : "+sensorData);
            data.put("doorsensor",sensorData);
        }

        Optional<HomeData> gas = homeDataService.findByIdData("gas");
        if(!gas.isPresent()) {
            log.info("gas 받아오기 실패");
            data.put("gas","");
        }else{
            sensorData = gas.get();
            log.info("가스 데이터 : "+gas);
            data.put("gas",sensorData);
        }

        Optional<HomeData> smoke = homeDataService.findByIdData("smoke");
        if(!smoke.isPresent()) {
            log.info("smoke 받아오기 실패");
            data.put("smoke","");
        }else{
            sensorData = smoke.get();
            log.info("화재 데이터 : "+smoke);
            data.put("smoke",sensorData);
        }

        Optional<HomeData> battery = homeDataService.findByIdData("battery");
        if(!battery.isPresent()) {
            log.info("battery 받아오기 실패");
            data.put("battery","");
        }else{
            sensorData = battery.get();
            log.info("배터리 데이터 : "+sensorData);
            data.put("batteryData",sensorData);
        }

//        Consumer<HashMap<Object, Object>> pdk = (arg)->{  // 메시지를 받는 콜백 행위
//            arg.forEach((key, value)->{
//                System.out.println("값 : "+value);
//            });
//        };
//
//        MyMqttClient client = new MyMqttClient(pdk);
//
//        client.initConnectionLost( (arg)->{  // 서버와의 연결이 끊기면 동작
//            arg.forEach((key, value)->{
//                System.out.println( String.format("커넥션 끊김 키 -> %s, 값 -> %s", key, value) );
//            });
//        });
//
//        client.init(BROADWAVE_USERNAME, BROADWAVE_PASSWORD, BROADWAVE_URL,"notice");
//        client.subscribe("notice/alert");

        res.addResponse("data",data);
        return ResponseEntity.ok(res.success());

    }

//    //구독하기
//    public void mqttSubscribe(String uuid){
//        AjaxResponse res = new AjaxResponse();
//        HashMap<String, Object> data = new HashMap<>();
//
//        final Consumer<HashMap<Object, Object>> pdk = (arg)->{  //메시지를 받는 콜백 행위
//            arg.forEach((key, value)->{
//                System.out.println( String.format("메시지 도착 : 키 -> %s, 값 -> %s", key, value) );
//            });
//        };
//
//        MyMqttClient client = new MyMqttClient(pdk);  //해당 함수를 생성자로 넣어준다.
//
//        client.init(BROADWAVE_USERNAME, BROADWAVE_PASSWORD, BROADWAVE_URL,uuid);
//
//        client.initConnectionLost( (arg)->{  //콜백행위1, 서버와의 연결이 끊기면 동작
//            arg.forEach((key, value)->{
//                System.out.println( String.format("커넥션 끊김 키 -> %s, 값 -> %s", key, value) );
//            });
//        });
//
//        client.initDeliveryComplete((arg)-> {  //콜백행위2, 메시지를 전송한 이후 동작
//            arg.forEach((key, value)->{
//                System.out.println( String.format("메시지 전달 완료 키 -> %s, 값 -> %s", key, value) );
//                data.put("value",value);
//                res.addResponse("data",data);
//            });
//        });
//
//        new Thread( ()->{
//            try {
//                Thread.sleep(100);
//                boolean sub = client.subscribe("notice/alert");
//                log.info("경고창 구독여부 : "+sub);
////                client.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }).start();
//    }

    @PostMapping("lightOnOff")
    public ResponseEntity<Map<String,Object>> lightOnOff(@RequestParam(value="value", defaultValue="") String value) throws MqttException {
        AjaxResponse res = new AjaxResponse();

        log.info("조명 명령하기 : "+value);

        final Consumer<HashMap<Object, Object>> pdk = (arg)->{};

        MyMqttClient client = new MyMqttClient(pdk);  //해당 함수를 생성자로 넣어준다.
        client.init(BROADWAVE_USERNAME, BROADWAVE_PASSWORD, BROADWAVE_URL, "command");

        new Thread( ()->{
            try {
                Thread.sleep(100);
                if(value.equals("ON")){
                    client.sender("command/smart/lighton","{\"Dashboard\":\"lightONCommand\"}");
                }else{
                    client.sender("command/smart/lightoff","{\"Dashboard\":\"lightOFFCommand\"}");
                }
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        return ResponseEntity.ok(res.success());
    }

    @PostMapping("aqOnOff")
    public ResponseEntity<Map<String,Object>> aqOnOff(@RequestParam(value="value", defaultValue="") String value) throws MqttException {
        AjaxResponse res = new AjaxResponse();
//        MyMqttClient client = new MyMqttClient();
        log.info("공기청정기 명령하기 : "+value);

        final Consumer<HashMap<Object, Object>> pdk = (arg)->{};

        MyMqttClient client = new MyMqttClient(pdk);  //해당 함수를 생성자로 넣어준다.
        client.init(BROADWAVE_USERNAME, BROADWAVE_PASSWORD, BROADWAVE_URL, "command");

        new Thread( ()->{
            try {
                Thread.sleep(100);
                if(value.equals("ON")){
                    client.sender("command/smart/aqon","{\"Dashboard\":\"aqONCommand\"}");
                }else{
                    client.sender("command/smart/aqoff","{\"Dashboard\":\"aqOFFCommand\"}");
                }
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        return ResponseEntity.ok(res.success());
    }

    @PostMapping("opendoor")
    public ResponseEntity<Map<String,Object>> opendoor(@RequestParam(value="value", defaultValue="") String value) throws MqttException {
        AjaxResponse res = new AjaxResponse();
//        MyMqttClient client = new MyMqttClient();
        log.info("문열기 명령하기 : "+value);

        final Consumer<HashMap<Object, Object>> pdk = (arg)->{};

        MyMqttClient client = new MyMqttClient(pdk);  //해당 함수를 생성자로 넣어준다.
        client.init(BROADWAVE_USERNAME, BROADWAVE_PASSWORD, BROADWAVE_URL, "command");

        new Thread( ()->{
            try {
                Thread.sleep(100);
                if(value.equals("OPEN")){
                    client.sender("command/door1/unlock","{\"Dashboard\":\"openDoorCommand\"}");
                }
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        return ResponseEntity.ok(res.success());
    }

    @PostMapping("airOnOff")
    public ResponseEntity<Map<String,Object>> airOnOff(@RequestParam(value="value", defaultValue="") String value){
        AjaxResponse res = new AjaxResponse();
        log.info("에어컨 명령하기 : "+value);

        final Consumer<HashMap<Object, Object>> pdk = (arg)->{};

        MyMqttClient client = new MyMqttClient(pdk);  //해당 함수를 생성자로 넣어준다.
        client.init(BROADWAVE_USERNAME, BROADWAVE_PASSWORD, BROADWAVE_URL, "command");

        new Thread( ()->{
            try {
                Thread.sleep(100);
                if(value.equals("ON")){
                    client.sender("command/smart/allairconon","{\"Dashboard\":\"airONCommand\"}");
                }else{
                    client.sender("command/smart/allairconoff","{\"Dashboard\":\"airOFFCommand\"}");
                }
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        return ResponseEntity.ok(res.success());
    }
    @PostMapping("air1OnOff")
    public ResponseEntity<Map<String,Object>> air1OnOff(@RequestParam(value="value", defaultValue="") String value){
        AjaxResponse res = new AjaxResponse();
        log.info("에어컨1(거실) 명령하기 : "+value);

        final Consumer<HashMap<Object, Object>> pdk = (arg)->{};

        MyMqttClient client = new MyMqttClient(pdk);  //해당 함수를 생성자로 넣어준다.
        client.init(BROADWAVE_USERNAME, BROADWAVE_PASSWORD, BROADWAVE_URL, "command");

        new Thread( ()->{
            try {
                Thread.sleep(100);
                if(value.equals("ON")){
                    client.sender("command/smart/aircon/rm1on","{\"Dashboard\":\"airONCommand\"}");
                }else{
                    client.sender("command/smart/aircon/rm1off","{\"Dashboard\":\"airOFFCommand\"}");
                }
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        return ResponseEntity.ok(res.success());
    }
    @PostMapping("air2OnOff")
    public ResponseEntity<Map<String,Object>> air2OnOff(@RequestParam(value="value", defaultValue="") String value){
        AjaxResponse res = new AjaxResponse();
        log.info("에어컨2(주방) 명령하기 : "+value);

        final Consumer<HashMap<Object, Object>> pdk = (arg)->{};

        MyMqttClient client = new MyMqttClient(pdk);  //해당 함수를 생성자로 넣어준다.
        client.init(BROADWAVE_USERNAME, BROADWAVE_PASSWORD, BROADWAVE_URL, "command");

        new Thread( ()->{
            try {
                Thread.sleep(100);
                if(value.equals("ON")){
                    client.sender("command/smart/aircon/rm2on","{\"Dashboard\":\"airONCommand\"}");
                }else{
                    client.sender("command/smart/aircon/rm2off","{\"Dashboard\":\"airOFFCommand\"}");
                }
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        return ResponseEntity.ok(res.success());
    }
    @PostMapping("air3OnOff")
    public ResponseEntity<Map<String,Object>> air3OnOff(@RequestParam(value="value", defaultValue="") String value){
        AjaxResponse res = new AjaxResponse();
        log.info("에어컨3(안방) 명령하기 : "+value);

        final Consumer<HashMap<Object, Object>> pdk = (arg)->{};

        MyMqttClient client = new MyMqttClient(pdk);  //해당 함수를 생성자로 넣어준다.
        client.init(BROADWAVE_USERNAME, BROADWAVE_PASSWORD, BROADWAVE_URL, "command");

        new Thread( ()->{
            try {
                Thread.sleep(100);
                if(value.equals("ON")){
                    client.sender("command/smart/aircon/rm3on","{\"Dashboard\":\"airONCommand\"}");
                }else{
                    client.sender("command/smart/aircon/rm3off","{\"Dashboard\":\"airOFFCommand\"}");
                }
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        return ResponseEntity.ok(res.success());
    }

    @GetMapping(path="mqttsubcribe",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseBodyEmitter mqttSubcribe() {
        SseEmitter emitter = new SseEmitter(-1L);
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
        sseMvcExecutor.execute(() -> {
            try {

                //String clientid ="LoaclDashboartClient";

                AtomicInteger atomicInt = new AtomicInteger(0);
                final Consumer<HashMap<Object, Object>> pdk = (arg)->{  //메시지를 받는 콜백 행위
                    arg.forEach((key, value)->{
                        //System.out.println( String.format("메시지 도착 : 키 -> %s, 값 -> %s", key, value) );
                        log.info("MQTT 메세지 수신 :" + key + " / " + value );
                        try {
                            //최초메세지는 안보내고 두번째 메세지부터 보낸다
                            if (key == "message" && atomicInt.get() == 1) {
                                emitter.send(value);
                            }
                            if (key == "message" && atomicInt.get() == 0) {
                                atomicInt.set(1);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                };
                MyMqttClient client = new MyMqttClient(pdk);  //해당 함수를 생성자로 넣어준다.

                client.init(BROADWAVE_USERNAME, BROADWAVE_PASSWORD, BROADWAVE_URL,BROADWAVE_CLIENTID);

                client.initConnectionLost( (arg)->{  //콜백행위1, 서버와의 연결이 끊기면 동작
                    arg.forEach((key, value)->{
                        //System.out.println( String.format("커넥션 끊김 키 -> %s, 값 -> %s", key, value) );
                        log.error("notice/alert 컨넥션 끊킴 ");
                        //client.init(BROADWAVE_USERNAME, BROADWAVE_PASSWORD, BROADWAVE_URL,clientid);
                    });
                });
//                new Thread( ()->{
//                    try {
//                        Thread.sleep(100);
//                        boolean sub = client.subscribe("notice/alert");
//                        log.info("경고창 구독여부 : "+sub);
//                        //client.close();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }).start();

                client.subscribe("notice/alert");
//                client.close();
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }
        });
        return emitter;

    }

//    @PostMapping("warning")
//    public ResponseEntity<Map<String,Object>> warning() throws MqttException {
//        AjaxResponse res = new AjaxResponse();
//        MyMqttClient client = new MyMqttClient();
//
//        log.info("경고메세지 보내기");
//        client.init(BROADWAVE_USERNAME, BROADWAVE_PASSWORD, BROADWAVE_URL, "command");
//        client.sender("notice/alert","{\"test\":\"test\"}");
//
//
//        return ResponseEntity.ok(res.success());
//    }

}

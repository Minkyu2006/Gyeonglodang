package kr.co.broadwave.homeservice.api.watch;

import kr.co.broadwave.homeservice.common.AjaxResponse;
import kr.co.broadwave.homeservice.common.ResponseErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author InSeok
 * Date : 2021-01-04
 * Remark : 애플워치 재실 등록 API서버 (api/v1/watch)
 */
@RestController
@RequestMapping("api/v1/watch")
@Slf4j
public class WatchRestcontroller {

    @Value("${broadwave.ha.iot.username}")
    private String BROADWAVE_USERNAME;
    @Value("${broadwave.ha.iot.password}")
    private String BROADWAVE_PASSWORD;
    @Value("${broadwave.ha.iot.url}")
    private String BROADWAVE_URL;

    private final WatchService watchService;

    @Autowired
    public WatchRestcontroller(WatchService watchService) {
        this.watchService = watchService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> watchReg(@RequestBody WatchMapper watchMapper) {
        AjaxResponse res = new AjaxResponse();
        if (watchMapper.getId() == null || watchMapper.getId().trim().isEmpty()
                || watchMapper.getName() == null || watchMapper.getName().trim().isEmpty()
        ) {
            return ResponseEntity.ok(res.fail(ResponseErrorCode.E001.getCode(), ResponseErrorCode.E001.getDesc()));
        }
        watchService.save(watchMapper);
        return ResponseEntity.ok(res.success());
    }
    @GetMapping
    public List<Watch> watchGetAll(){
        return watchService.getAll();
    }


//    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public ResponseBodyEmitter watch() {
//        SseEmitter emitter = new SseEmitter();
//        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
//        sseMvcExecutor.execute(() -> {
//            try {
//
//                String clientid ="testuuid";
//
//                AtomicInteger atomicInt = new AtomicInteger(0);
//                final Consumer<HashMap<Object, Object>> pdk = (arg)->{  //메시지를 받는 콜백 행위
//                    arg.forEach((key, value)->{
//                        System.out.println( String.format("메시지 도착 : 키 -> %s, 값 -> %s", key, value) );
//                        try {
//                            //최초메세지는 안보내고 두번째 메세지부터 보낸다
//
//                            if (key == "message" && atomicInt.get() == 1) {
//                                emitter.send(value);
//                            }
//                            if (key == "message" && atomicInt.get() == 0) {
//                                atomicInt.set(1);
//                            }
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    });
//                };
//                MyMqttClient client = new MyMqttClient(pdk);  //해당 함수를 생성자로 넣어준다.
//
//                client.init(BROADWAVE_USERNAME, BROADWAVE_PASSWORD, BROADWAVE_URL,clientid);
//
//                client.initConnectionLost( (arg)->{  //콜백행위1, 서버와의 연결이 끊기면 동작
//                    arg.forEach((key, value)->{
//                        System.out.println( String.format("커넥션 끊김 키 -> %s, 값 -> %s", key, value) );
//                    });
//                });
//
//                boolean sub = client.subscribe("notice/alert");
//
//            } catch (Exception ex) {
//                emitter.completeWithError(ex);
//            }
//        });
//        return emitter;
//
//    }
//////////=============mqtt 테스트
//    @RequestMapping("mqtt")
//    public String mqttsubcribe(){
////        AjaxResponse res = new AjaxResponse();
////        HashMap<String, Object> data = new HashMap<>();
//        String clientid ="testuuid";
//
//        final Consumer<HashMap<Object, Object>> pdk = (arg)->{  //메시지를 받는 콜백 행위
//            arg.forEach((key, value)->{
//                System.out.println( String.format("메시지 도착 : 키 -> %s, 값 -> %s", key, value) );
//            });
//        };
//
//        MyMqttClient client = new MyMqttClient(pdk);  //해당 함수를 생성자로 넣어준다.
//
//        client.init(BROADWAVE_USERNAME, BROADWAVE_PASSWORD, BROADWAVE_URL,clientid);
//
//        client.initConnectionLost( (arg)->{  //콜백행위1, 서버와의 연결이 끊기면 동작
//            arg.forEach((key, value)->{
//                System.out.println( String.format("커넥션 끊김 키 -> %s, 값 -> %s", key, value) );
//            });
//        });
//
////        client.initDeliveryComplete((arg)-> {  //콜백행위2, 메시지를 전송한 이후 동작
////            arg.forEach((key, value)->{
////                System.out.println( String.format("메시지 전달 완료 키 -> %s, 값 -> %s", key, value) );
////                data.put("value",value);
////                res.addResponse("data",data);
////            });
////        });
//        boolean sub = client.subscribe("notice/alert");
//
////        new Thread( ()->{
////            try {
////                Thread.sleep(100);
////                boolean sub = client.subscribe("notice/alert");
////                log.info("경고창 구독여부 : "+sub);
//////                client.close();
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////        }).start();
//
//        return "MQTTTEST";
//    }



}

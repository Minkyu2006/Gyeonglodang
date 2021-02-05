package kr.co.broadwave.homeservice.admin;

import kr.co.broadwave.homeservice.common.AjaxResponse;
import kr.co.broadwave.homeservice.common.CommonUtils;
import kr.co.broadwave.homeservice.homedata.HomeData;
import kr.co.broadwave.homeservice.homedata.HomeDataService;
import kr.co.broadwave.homeservice.mqttsetting.MyMqttClient;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
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
    private final ModelMapper modelMapper;
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminHomeDataRestController(HomeDataService homeDataService,
                                       ModelMapper modelMapper,
                                       AccountService accountService,
                                       PasswordEncoder passwordEncoder) {
        this.homeDataService = homeDataService;
        this.modelMapper = modelMapper;
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
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
            data.put("humidity",sensorData);
        }

        res.addResponse("data",data);
        return ResponseEntity.ok(res.success());
    }

    // 관리자페이지 전용(메뉴데이터)
    @PostMapping("adminMenuDataInfo")
    public ResponseEntity<Map<String,Object>> adminMenuDataInfo(){

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

        Optional<HomeData> airquality = homeDataService.findByIdData("airquality10");
        if(!airquality.isPresent()) {
            log.info("airquality 받아오기 실패");
            data.put("airquality","");
        }else{
            sensorData = airquality.get();
            log.info("공기질 데이터 : "+sensorData);
            data.put("airquality",sensorData);
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

        Optional<HomeData> aircondition = homeDataService.findByIdData("aircondition");
        if(!aircondition.isPresent()) {
            log.info("aircondition 받아오기 실패");
            data.put("aircondition","");
        }else{
            sensorData = aircondition.get();
            log.info("날씨 데이터 : "+sensorData);
            data.put("aircondition",sensorData);
        }

        Optional<HomeData> battery = homeDataService.findByIdData("battery");
        if(!battery.isPresent()) {
            log.info("batteryData 받아오기 실패");
        }else{
            sensorData = battery.get();
            log.info("온도 데이터 : "+sensorData);
            data.put("batteryData",sensorData);
        }

        Optional<HomeData> electric = homeDataService.findByIdData("electric");
        if(!electric.isPresent()) {
            log.info("electric 받아오기 실패");
        }else{
            sensorData = electric.get();
            log.info("가전전원 데이터 : "+electric);
            data.put("electricDate",sensorData);
        }

        res.addResponse("data",data);
        return ResponseEntity.ok(res.success());
    }

    // admin 비밀번호변경
    @Transactional
    @PostMapping("modifypassword")
    public ResponseEntity<Map<String,Object>> modifypassword(@ModelAttribute AccountMapperDtoModify accountMapperDtoModify,
                                                             HttpServletRequest request){
        AjaxResponse res = new AjaxResponse();

        Account account = modelMapper.map(accountMapperDtoModify, Account.class);
        String currentuserid = CommonUtils.getCurrentuser(request);

        Optional<Account> optionalAccount = accountService.findByUserid(currentuserid);

        if(optionalAccount.isPresent()){
            //현재암호비교
            if (!passwordEncoder.matches(accountMapperDtoModify.getOldpassword(),optionalAccount.get().getPassword())){
                return ResponseEntity.ok(res.fail("", "현재 비밀번호가 일치하지 않습니다."));
            }
            //신규암호같은지 확인
            if( !accountMapperDtoModify.getNewpassword().equals(accountMapperDtoModify.getPasswordconfirm()) ){
                return ResponseEntity.ok(res.fail("", "신규 비밀번호가 일치하지 않습니다."));
            }
            account.setId(optionalAccount.get().getId());
            account.setUserid(currentuserid);
            account.setRole(optionalAccount.get().getRole());
            account.setInsertDateTime(optionalAccount.get().getInsertDateTime());
            account.setPassword(accountMapperDtoModify.getPasswordconfirm());
        }
        account.setModifyDateTime(LocalDateTime.now());
        accountService.saveAccount(account);
        return ResponseEntity.ok(res.success());

    }

}

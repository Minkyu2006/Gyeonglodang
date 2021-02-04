package kr.co.broadwave.homeservice.api.system;

import kr.co.broadwave.homeservice.common.AjaxResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/apiData")
public class SystemRestController {

    // Json타입 데이터가져오기
    public static String callURL(String myURL) {

//        System.out.println("Requeted URL:" + myURL);
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;

        HostnameVerifier allHostsValid = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

        try {
            URL url = new URL(myURL);
            urlConn = url.openConnection();
            if (urlConn != null)
                urlConn.setReadTimeout(60 * 1000);
            if (urlConn != null && urlConn.getInputStream() != null) {
                in = new InputStreamReader(urlConn.getInputStream(), Charset.defaultCharset());
                //charset 문자 집합의 인코딩을 사용해 urlConn.getInputStream을 문자스트림으로 변환 객체를 생성.
                BufferedReader bufferedReader = new BufferedReader(in);
                //주어진 문자 입력 스트림 inputStream에 대해 기본 크기의 버퍼를 갖는 객체를 생성.
                if (bufferedReader != null) {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("Exception URL:"+ myURL, e);
        }
        return sb.toString();
    }

    // 운영정책 데이터 가져오기
    @PostMapping("operationGet")
    public ResponseEntity<Map<String,Object>> operationGet(@RequestParam(value="value", defaultValue="") String value){
        AjaxResponse res = new AjaxResponse();
        HashMap<String, Object> data = new HashMap<>();
        log.info("value : "+value);
        data.put("operationDate",callURL(value));
        res.addResponse("data",data);
        return ResponseEntity.ok(res.success());
    }

    // 운영정책 데이터 보내기
    @PostMapping("operationPut")
    public ResponseEntity<Map<String,Object>> operationPut(){
        AjaxResponse res = new AjaxResponse();

        // todo http://square.abrain.co.kr:8080/square/operation/commonSetting?system_id=S002

        return ResponseEntity.ok(res.success());
    }

}

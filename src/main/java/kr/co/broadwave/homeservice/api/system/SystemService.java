package kr.co.broadwave.homeservice.api.system;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

@Slf4j
@Service
public class SystemService {

    // Json타입 데이터가져오기
    public String callURL(String myURL) {

//        System.out.println("Requeted URL:" + myURL);
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn;
        InputStreamReader in = null;

        HostnameVerifier allHostsValid = (hostname, session) -> true;

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
                int cp;
                while ((cp = bufferedReader.read()) != -1) {
                    sb.append((char) cp);
                }
                bufferedReader.close();
            }
            assert in != null;
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("Exception URL:"+ myURL, e);
        }
        return sb.toString();
    }

}

package kr.co.broadwave.homeservice.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.broadwave.homeservice.common.CommonUtils;
import kr.co.broadwave.homeservice.configs.ResponseDataCode;
import kr.co.broadwave.homeservice.configs.ResponseDataDTO;
import kr.co.broadwave.homeservice.configs.ResponseDataStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {

        String login_ip = CommonUtils.getIp(request);
        log.info("로그인 실패 : '" + request.getParameter("username") +"' 아이피? :'" + login_ip + "'");

        ObjectMapper mapper = new ObjectMapper();

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        responseDataDTO.setCode(ResponseDataCode.ERROR);
        responseDataDTO.setStatus(ResponseDataStatus.ERROR);
        responseDataDTO.setMessage("비밀번호가 일치하지 않습니다.");

        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print(mapper.writeValueAsString(responseDataDTO));
        response.getWriter().flush();

    }
}
package kr.co.broadwave.homeservice.handler;


import kr.co.broadwave.homeservice.common.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {
        String login_ip = CommonUtils.getIp(req);
        log.info("로그인 실패 : '" + req.getParameter("username") +"' 아이피? :'" + login_ip + "'");
        req.getRequestDispatcher("/login?error=true").forward(req, res);
    }

}

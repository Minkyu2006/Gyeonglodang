package kr.co.broadwave.homeservice.configs;

import kr.co.broadwave.homeservice.handler.CustomAuthenticationFailureHandler;
import kr.co.broadwave.homeservice.handler.CustomAuthenticationSuccessHandler;
import kr.co.broadwave.homeservice.handler.LoginFailureHandler;
import kr.co.broadwave.homeservice.handler.LoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author Minkyu
 * Date : 2020-02-04
 * Time :
 * Remark : Security 관련 config
 */

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;


    @Override
    public void configure(WebSecurity web) throws Exception {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/admindashboard/**").hasAnyRole("ADMIN")
                .antMatchers("/login").permitAll()
                .anyRequest().permitAll()

                .and()
                .formLogin()
                    .loginPage("/login")
                    .usernameParameter("username")	//스프링 시큐리티에서는 username을 기본 아이디 매핑 값으로 사용하는데 이거 쓰면 변경
                    .passwordParameter("password")
                    .successHandler(customAuthenticationSuccessHandler)
                    .failureHandler(customAuthenticationFailureHandler)
        ;
    }

//    @Bean
//    public AuthenticationSuccessHandler successHandler() {
//        return new LoginSuccessHandler("/loginsuccess");
//    }
//
//    @Bean
//    public AuthenticationFailureHandler failureHandler() {
//        return new LoginFailureHandler();
//    }

}

package kr.co.broadwave.homeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class HomeserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(HomeserviceApplication.class, args);
    }
}

package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Spring Boot应用程序主类
 */
@SpringBootApplication
@EnableScheduling//@EnableScheduling 注解，开启了定时任务功能
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

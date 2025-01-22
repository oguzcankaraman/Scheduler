package com.schedule.schedulebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ScheduleBackendApplication {
//TODO: CREATE CUSTOM EXCEPTIONS AND MODIFY CONTROLLERS
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:5173/");
            }
        };
    }
//TODO: PROGRAMLAR ÖĞRENCİYE BAĞLI Öğrenci ad soyad numara
// TODO: derslerin kaçıncı sınıf olduğunu ekle

    public static void main(String[] args) {
        SpringApplication.run(ScheduleBackendApplication.class, args);
    }

}

package com.luke.web.tj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AppTJ {

    public static void main(String[] args) {
        SpringApplication.run(AppTJ.class,args) ;
    }
}

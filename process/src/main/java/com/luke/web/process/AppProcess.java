package com.luke.web.process;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AppProcess {

    public static void main(String[] args) {
        SpringApplication.run(AppProcess.class,args) ;
    }
}

package com.ggx.dev;

import com.ggx.dev.configs.AppConfig;
import com.ggx.dev.services.Handler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Handler handler = context.getBean(Handler.class);
        handler.handleInputOutput();
    }
}
package com.ggx.dev;

import com.ggx.dev.controllers.ChatController;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLOutput;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    private SettingsLoaderService loaderService;
    @Autowired
    private ChatController chatController;
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @PostConstruct
    public void initialize(){
        System.out.println("Loaded settings: " + loaderService.getSettings());
    }

    @Override
    public void run(String... args) throws Exception {
        chatController.startChat();
    }
}
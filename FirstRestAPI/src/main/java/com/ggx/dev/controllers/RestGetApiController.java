package com.ggx.dev.controllers;

import com.ggx.dev.UserProfile;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestGetApiController {

    private List<UserProfile> userProfiles;

    @PostConstruct
    public void init(){
        userProfiles = new ArrayList<>();
        userProfiles.add(new UserProfile("Guga", "my.email@gmail.com", 200, "Brasil" ));
        userProfiles.add(new UserProfile("João", "joao.email@gmail.com", 30, "EUA" ));
        userProfiles.add(new UserProfile("Antony", "antony.email@gmail.com", 19, "Japão" ));

    }


    @GetMapping
    public String getFunc(){
        return "<h1>Hello again!</h1>";
    }

    @GetMapping("/users")
    public ResponseEntity<UserProfile> getProfileByName(
            @RequestParam(value="name") String name
    ){

        var user = userProfiles.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElse(null);

        if(user != null){
            System.out.println("Get request received! user: " + name);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else{
            System.out.println("Get request received! but not found");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }
}

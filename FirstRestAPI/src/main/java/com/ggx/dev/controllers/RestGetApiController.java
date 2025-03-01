package com.ggx.dev.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestGetApiController {

    @GetMapping
    public String getFunc(){
        return "<h1>Hello again!</h1>";
    }
}

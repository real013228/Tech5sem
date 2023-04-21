package com.real013228.controller;

import com.real013228.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    @Autowired
    private GreetingService greetingService;
    @GetMapping("/hello")
    public String getGreetings() {
        return greetingService.sayHello();
    }
}

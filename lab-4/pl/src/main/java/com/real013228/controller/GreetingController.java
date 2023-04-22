package com.real013228.controller;

import com.real013228.service.GreetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hello")
public class GreetingController {
    @Autowired
    private GreetingService greetingService;
    @GetMapping("/hello")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getGreetings() {
        return greetingService.sayHello();
    }
}

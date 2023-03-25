package org.real013228.lab3.pl.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetGreetingsController {
    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String getGreetings() {
        return "Hello, my friend";
    }
}

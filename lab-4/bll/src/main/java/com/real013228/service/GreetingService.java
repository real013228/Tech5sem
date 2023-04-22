package com.real013228.service;

import com.real013228.repository.CatRepository;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    private final CatRepository catRepository;

    public GreetingService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public String sayHello() {
        return "Hello, my friend!";
    }
}

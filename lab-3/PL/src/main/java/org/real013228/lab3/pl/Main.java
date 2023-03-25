package org.real013228.lab3.pl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan("org.real013228.lab3.bll.services.implementations")
@ComponentScan("org.real013228.lab3")
@EntityScan("org.real013228.dal")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
//package org.real013228.lab3.pl.config;
//
//import org.real013228.lab3.bll.services.implementations.CreateCatImplementation;
//import org.real013228.lab3.pl.controllers.CreateCatController;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
//import org.thymeleaf.spring5.view.ThymeleafViewResolver;
//
//@Configuration
//@ComponentScan("org.real013228.lab3")
//@EnableWebMvc
//public class SpringControllerConfig {
//    @Bean
//    public CreateCatController createCatController() {
//        return new CreateCatController(new CreateCatImplementation());
//    }
//}
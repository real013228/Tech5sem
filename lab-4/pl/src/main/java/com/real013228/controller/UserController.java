//package com.real013228.controller;
//
//import com.real013228.dto.UserDto;
//import com.real013228.service.UserDetailService;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/users")
//public class UserController {
//    private final UserDetailService userService;
//
//    public UserController(UserDetailService userService) {
//        this.userService = userService;
//    }
//    @GetMapping("/get-all")
//    public List<UserDto> getAllUsers() {
//        return userService.findAll();
//    }
//}

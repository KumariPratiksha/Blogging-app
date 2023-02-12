package com.pratiksha.blogApp.controllers;

import com.pratiksha.blogApp.payloads.UserDto;
import com.pratiksha.blogApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;




}

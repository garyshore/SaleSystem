package com.shore.distribution.controller;

import com.shore.distribution.service.UsersService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shore.distribution.entity.Users;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UsersService usersService;

    @RequestMapping("/v1/register")
    public int register(@RequestBody Users user){ return usersService.register(user); }

    @RequestMapping("/v1/login")
    public Users login(@RequestBody Users user){ return usersService.login(user);}

}
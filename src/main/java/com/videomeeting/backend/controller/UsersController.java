package com.videomeeting.backend.controller;

import com.videomeeting.backend.entity.Users;
import com.videomeeting.backend.requests.LoginRequest;
import com.videomeeting.backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {
        "https://meetx-frontend-alpha.vercel.app",
        "https://meetx-frontend-git-main-charmingprabhats-projects.vercel.app"
})
public class UsersController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public String addUser(@RequestBody Users user) {

        return userService.addUser(user);
    }

    @PostMapping("/loginUser")
    public String loginUser(@RequestBody LoginRequest request) {

        return userService.loginUser(request);
    }
}
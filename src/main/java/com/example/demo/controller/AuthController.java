package com.example.demo.controller;

import com.example.demo.dao.Result;
import com.example.demo.pojo.User;
import com.example.demo.pojo.UserDTO;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public Result<UserDTO> login(@RequestBody UserDTO loginUser) {
        return authService.login(loginUser);
    }

    @PostMapping("/register")
    public Result<User> register(@RequestBody UserDTO registerUser) {
        User user = authService.register(registerUser);
        if (user != null){
            return Result.success(user);
        }
        return Result.error(Result.PARAM_ERROR,"用户名已存在");
    }

    @PutMapping("/{username}/logout")
    public Result<User> logout(@PathVariable String username) {
        if(authService.logout(username)){
            return Result.success();
        }
        return Result.error(Result.FORBIDDEN,"无法登出");
    }


}

package com.Userservice.controller;


import com.Userservice.dto.UserRequest;
import com.Userservice.dto.UserResponse;
import com.Userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping
    public void save(@RequestBody UserRequest userRequest){
        userService.AddUser(userRequest);
    }
    @GetMapping
    public List<UserResponse> userResponses(){
       return userService.getAllUsers();
    }

}

package com.Userservice.controller;


import com.Userservice.dto.LoggedInDto;
import com.Userservice.dto.UserRequest;
import com.Userservice.dto.UserResponse;
import com.Userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/logedIn")
    public ResponseEntity<String> loginUser(@RequestBody LoggedInDto dto){
        try{
            userService.SignIn(dto);
            return ResponseEntity.ok("Login successfully! Welcome");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());

        }
    }

}

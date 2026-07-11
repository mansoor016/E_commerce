package com.Userservice.service;

import com.Userservice.Repo.UserRepository;
import com.Userservice.dto.LoggedInDto;
import com.Userservice.dto.LoginRequest;
import com.Userservice.model.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//@Service
//@RequiredArgsConstructor
public class AuthService {
//    private final UserRepository userRepository;
//    private final JwtService jwtService;
//    private final PasswordEncoder passwordEncoder;
//
//    public LoginRequest login(LoggedInDto request) {
//
//        // 1. Email se user dhundo
//        Users user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() ->
//                        new RuntimeException("User not found!"));
//
//        // 2. Password match karo
//        if (!passwordEncoder.matches(
//                request.getPass(), user.getPassword())) {
//            throw new RuntimeException("Invalid password!");
//        }
//
//        // 3. Token generate karo
//        String token = jwtService.generateToken(user.getEmail());
//
//        // 4. Token return karo
//        return LoginRequest.builder()
//                .token(token)
//                .email(user.getEmail())
//                .message("Login successful!")
//                .build();
//    }
}

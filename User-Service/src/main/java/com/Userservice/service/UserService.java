package com.Userservice.service;

import com.Userservice.Repo.UserRepository;
import com.Userservice.dto.UserRequest;
import com.Userservice.dto.UserResponse;
import com.Userservice.model.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public void AddUser(UserRequest userRequest){
        Users users = Users.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .phone(userRequest.getPhone())
                .address(userRequest.getAddress())
                .role(userRequest.getRole())
                .build();
        userRepository.save(users);
    }

    public List<UserResponse> getAllUsers(){
        List<Users> users = userRepository.findAll();
        return users.stream().map(this::toUsersRespose).toList();
    }

    public UserResponse toUsersRespose(Users users){
       return UserResponse.builder().id(users.getId())
               .firstName(users.getFirstName())
               .lastName(users.getLastName())
               .email(users.getEmail())
               .password(users.getPassword())
               .phone(users.getPhone())
               .address(users.getAddress())
               .role(users.getRole())
               .createdAt(users.getCreatedAt())
               .updatedAt(users.getUpdatedAt()).build();
    }
}

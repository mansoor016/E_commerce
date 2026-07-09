package com.Userservice.service;

import com.Userservice.Repo.UserRepository;
import com.Userservice.dto.LoggedInDto;
import com.Userservice.dto.UserRequest;
import com.Userservice.dto.UserResponse;
import com.Userservice.model.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

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

    public boolean SignIn(LoggedInDto dto){

        Users user  = userRepository.findByEmail(dto.getEmail()).orElseThrow(()->new RuntimeException("user not found with this email "+ dto
                .getEmail()));
        System.out.println("DB passowrd "+ user.getPassword());
        System.out.println("passowrd given "+ dto.getPass());
        if(!user.getPassword().equals(dto.getPass())){
            throw new RuntimeException("password is wrong");
        }
        System.out.println("email is successfull");
        return true;

    }
}

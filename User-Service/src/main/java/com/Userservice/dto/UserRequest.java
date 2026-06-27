package com.Userservice.dto;

import com.Userservice.model.Roles;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String address;
    private Roles role;

}

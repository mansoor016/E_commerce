package com.Userservice.Repo;

import com.Userservice.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {


    Optional<Users> findByEmail(String email);

}

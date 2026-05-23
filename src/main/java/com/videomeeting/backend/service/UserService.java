package com.videomeeting.backend.service;

import com.videomeeting.backend.entity.Users;
import com.videomeeting.backend.repository.UsersRepo;
import com.videomeeting.backend.requests.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UsersRepo usersRepo;

    public String addUser(
            Users user
    ) {

        Users existingUser =
                usersRepo.findByEmail(
                        user.getEmail()
                );

        if (existingUser != null) {

            return "Email already registered";
        }

        usersRepo.save(user);

        return "Registration successful";
    }

    public String loginUser(
            LoginRequest request
    ) {

        Users user =
                usersRepo.findByEmail(
                        request.getEmail()
                );

        if (
                user == null
        ) {

            return "Invalid User";
        }

        if (
                !user.getPassword()
                        .equals(
                                request.getPassword()
                        )
        ) {

            return "Wrong Password";
        }

        return "Login Successful";
    }
}
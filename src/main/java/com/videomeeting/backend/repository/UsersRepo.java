package com.videomeeting.backend.repository;

import com.videomeeting.backend.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo
        extends JpaRepository<Users, Long> {

    Users findByEmail(
            String email
    );
}
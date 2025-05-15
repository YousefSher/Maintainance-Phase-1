package com.learning_management_system.repository;

import com.learning_management_system.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByEmail(String email);

}

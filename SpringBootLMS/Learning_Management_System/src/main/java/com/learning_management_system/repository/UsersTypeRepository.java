package com.learning_management_system.repository;

import com.learning_management_system.entity.UsersType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersTypeRepository extends JpaRepository<UsersType, Integer> {
}
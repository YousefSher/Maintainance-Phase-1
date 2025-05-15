package com.learning_management_system.controller;

import com.learning_management_system.entity.UsersType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
@RestController
public class UsersController{


@RequestMapping("/api/users")
 public ResponseEntity<List<UsersType>> getAllUserTypes() {

    return null;
}

}
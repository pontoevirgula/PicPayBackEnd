package com.chslcompany.picpaysimplificado.controllers;

import com.chslcompany.picpaysimplificado.domain.user.Users;
import com.chslcompany.picpaysimplificado.dtos.UserDTO;
import com.chslcompany.picpaysimplificado.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody UserDTO userDTO){
        Users newUser = this.userService.createUser(userDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public  ResponseEntity<List<Users>> getAllUsers(){
        List<Users> allUsers = this.userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
}

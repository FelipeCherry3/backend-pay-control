package com.cherryTest.backendpaycontrol.controllers;

import com.cherryTest.backendpaycontrol.domain.user.User;
import com.cherryTest.backendpaycontrol.dto.UserDTO;
import com.cherryTest.backendpaycontrol.services.UserService;
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
    @PostMapping("/cadastro")
    public ResponseEntity<User> createUser(@RequestBody UserDTO user){
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllusers(){
        List<User> usuarios = this.userService.getAlluser();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
}

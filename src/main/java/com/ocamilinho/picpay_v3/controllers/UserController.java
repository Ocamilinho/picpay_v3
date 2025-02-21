package com.ocamilinho.picpay_v3.controllers;

import com.ocamilinho.picpay_v3.services.UserService;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ocamilinho.picpay_v3.domains.DTOs.UserDTO;
import com.ocamilinho.picpay_v3.domains.DTOs.UserResponseDTO;



@RestController
@RequestMapping("user")
public class UserController {
    final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listAll(){
        List<UserResponseDTO> list = service.listAllUsers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserDTO data){
        UserResponseDTO user = service.createUser(data);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) throws Exception{
        service.deleteUser(UUID.fromString(id));
        return new ResponseEntity<Void>(HttpStatus.OK); 
    }
    @PutMapping("{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable String id,@RequestBody UserDTO data){
        UserResponseDTO user = service.updateUser(UUID.fromString(id) ,data);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}

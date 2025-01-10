package com.example.instagramclone.shop.controller;


import com.example.instagramclone.shop.repository.UserRepository;
import com.example.instagramclone.shop.service.UserService;
import com.example.instagramclone.shop.user.User;
import com.example.instagramclone.shop.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String  create(@RequestBody User user) {
        userService.createUser(user);
        return "insert success";
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

//    @GetMapping
//    public ResponseEntity<List<User>> getAllUser() {
//        List<User> users = userService.getAllUsers();
//        return ResponseEntity.ok(users);
//    }

    @GetMapping
    public ResponseEntity<?> UserList() {
        List<UserDto> users = new ArrayList<>(userService.getAllUsers())
                .stream()
                .map(u-> new UserDto(u))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(users);
    }

}

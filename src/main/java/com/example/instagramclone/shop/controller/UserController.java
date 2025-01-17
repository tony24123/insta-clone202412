package com.example.instagramclone.shop.controller;


import com.example.instagramclone.shop.repository.UserRepository;
import com.example.instagramclone.shop.service.LoginRequest;
import com.example.instagramclone.shop.service.UserService;
import com.example.instagramclone.shop.service.signUpRequest;
import com.example.instagramclone.shop.user.User;
import com.example.instagramclone.shop.user.UserDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //회원 생성(DB에 패스워드 암호화 X)
//    @PostMapping
//    public String  create(@RequestBody User user) {
//        userService.createUser(user);
//        return "insert success";
//    }

    //회원 한명 조회
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    //회원 전체 조회
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    //전체 회원 조회
    @GetMapping("/getall")
    public ResponseEntity<?> UserList() {
        List<UserDto> users = new ArrayList<>(userService.getAllUsers());
        return ResponseEntity.ok().body(users);
    }

//    //회원가입 요청 받아오기
//    @PostMapping("/auth/signup")
//    public ResponseEntity<?> signUp(@RequestBody @Valid signUpRequest signUpRequest){
//        log.info("request for signup : {}", signUpRequest.getNewName());
//        userService.signUp(signUpRequest);
//
//        return ResponseEntity
//                .ok()
//                .body("user registered!");
//    }
//
//    //로그인 요청 받아오기
//    @PostMapping("/auth/login")
//    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest){
//        Map<String, Object> responseMap = userService.authenticate(loginRequest);
//        return ResponseEntity.ok().body(responseMap);
//    }

////    @PostMapping("/register")
////public String  createUser(@RequestBody User user) {
////    userService.createUser(user);
////    return "insert success";
//}
}

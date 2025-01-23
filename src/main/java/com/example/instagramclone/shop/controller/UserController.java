package com.example.instagramclone.shop.controller;


import com.example.instagramclone.shop.repository.UserRepository;
import com.example.instagramclone.shop.service.LoginRequest;
import com.example.instagramclone.shop.service.UserService;
import com.example.instagramclone.shop.service.signUpRequest;
import com.example.instagramclone.shop.user.MeResponse;
import com.example.instagramclone.shop.user.User;
import com.example.instagramclone.shop.user.UserDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

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

    //로그인한 유저의 정보 조회
    @GetMapping("/currentUser")
    public ResponseEntity<MeResponse> getCurrentUser(
            @AuthenticationPrincipal String username
    ) {
        MeResponse responseDto = userService.currentLoggedInUser(username);

        return ResponseEntity.ok().body(responseDto);
    }

    //보유칩 업데이트
    //요청을 meResponse로 받는 것이 원칙에 어긋남 -> 요청을 받을 meRequest를 만들어야 함
    //클라이언트에서 받아오는 값 - 유저이름 , 보유 칩 개수
    //meResponse에 선언된 필드 중 id는 받아오지 않고 있음 id = null
    //문제가 발생하지 않는 것처럼 보이는 이유 :
    //Repository에서 update할때 코인 개수와 username만 필요로 하기 때문에 id가 null값으로 표기되고 있는데도 문제가 발생하지 않는다.
    @PutMapping("/updateChips")
    public  ResponseEntity<?> updateUserChips(
            @RequestBody @Valid MeResponse meResponse

    ){
        log.info("meResponse: {}", meResponse); //문제 상황 : meResponse에서 id값은 null로 표기되고 있다.
        userService.updateGameChips(meResponse);
        return ResponseEntity
                .ok()
                .body("chips Update!");
    }
}

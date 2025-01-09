package com.example.instagramclone.shop.service;

import com.example.instagramclone.shop.repository.UserRepository;
import com.example.instagramclone.shop.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

//    @Autowired
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    //유저 생성
    public void createUser(User user) {
        userRepository.insert(user);
    }

    //유저 조회
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
    }

    //전체 유저 조회
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

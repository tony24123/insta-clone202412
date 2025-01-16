package com.example.instagramclone.shop.service;

import com.example.instagramclone.shop.config.PasswordEncoderConfig;
import com.example.instagramclone.shop.repository.UserRepository;
import com.example.instagramclone.shop.user.User;
import com.example.instagramclone.shop.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(u -> new UserDto(u))
                .collect(Collectors.toList());
    }

    //회원가입 중간 처리
    public void signUp(signUpRequest signUpRequest){

        //순수 비밀번호
        String rawPassword = signUpRequest.getNewPassword();
        //암호화된 비밀번호
        String encodedPassword= passwordEncoder.encode(rawPassword);
        

        //회원정보
        User newUser = signUpRequest.toEntity();
        //암호화된 패스워드로 재설정
        newUser.setPassword(encodedPassword);
        //데이터베이스에 저장
        userRepository.insert(newUser);
    }
}

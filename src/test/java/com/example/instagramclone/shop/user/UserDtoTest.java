package com.example.instagramclone.shop.user;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {

    @Test
    void test() {
        User u = new User(1L, "라따뚜이", "latatuei@naver.com", "3456123456", LocalDateTime.now());

        UserDto dto = new UserDto(u);
        System.out.println("dto = " + dto);

    }


}
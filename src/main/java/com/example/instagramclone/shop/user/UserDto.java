package com.example.instagramclone.shop.user;


import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long userId;
    private String name;
    private String userEmail;
    private String userPassword;
    private String date;


    public UserDto(User u) {

        this.userId = u.getId();
        this.name = u.getUsername();
        this.userEmail = u.getEmail().length() > 5 ? u.getEmail().substring(0, 5) + "..." : u.getEmail();
        this.userPassword = u.getPassword().length()> 6 ? u.getPassword().substring(0,u.getPassword().length()-2) + "**" : u.getPassword() ;
        this.date = u.getCreateAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

    }
}
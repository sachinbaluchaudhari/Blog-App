package com.blog.app.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDto {
    private String userId;
    private String name;
    private String email;
    private String password;
    private String about;
}

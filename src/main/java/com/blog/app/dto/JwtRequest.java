package com.blog.app.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest {
    private String userName;
    private String password;
}

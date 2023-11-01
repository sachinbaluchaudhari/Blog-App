package com.blog.app.dto;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String jwtToken;
    private UserDto user;
}

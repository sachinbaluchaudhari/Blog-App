package com.blog.app.dto;

import com.blog.app.entities.Post;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String userId;
    private String name;
    private String email;
    private String password;
    private String about;
  // private List<PostDto> posts=new ArrayList<>();
}

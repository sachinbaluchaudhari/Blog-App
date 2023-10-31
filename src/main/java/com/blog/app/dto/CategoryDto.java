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
public class CategoryDto {
    private String categoryId;
    private String title;
    //private List<PostDto> posts=new ArrayList<>();
}

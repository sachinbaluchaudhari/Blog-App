package com.blog.app.dto;

import com.blog.app.entities.Category;
import com.blog.app.entities.User;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {
    private String postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private UserDto user;
    private CategoryDto category;
    private List<CommentDto> comments=new ArrayList<>();
}

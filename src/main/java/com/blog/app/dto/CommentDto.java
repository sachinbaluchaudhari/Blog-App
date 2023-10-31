package com.blog.app.dto;

import com.blog.app.entities.Post;
import com.blog.app.entities.User;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    private String commentId;
    private String comment;
    //private UserDto user;
    //private Post post;
}

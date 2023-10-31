package com.blog.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Posts")
public class Post {
    @Id
    private String postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category")
    private Category category;
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    //@JsonIgnore
    private List<Comment> comments=new ArrayList<>();



}

package com.blog.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Users")
public class User {
    @Id
    private String userId;
    //@Column(name = "user_name",nullable = false,length = 50)
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String about;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonIgnore
    private List<Post> posts=new ArrayList<>();
    @OneToMany(mappedBy = "user" )
    @JsonIgnore
    private List<Comment> comments=new ArrayList<>();

}

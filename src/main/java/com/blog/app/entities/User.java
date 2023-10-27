package com.blog.app.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
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
}

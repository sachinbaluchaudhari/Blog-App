package com.blog.app.repositories;

import com.blog.app.entities.Comment;
import com.blog.app.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,String> {
    Page<Comment> findByPost(Post post, Pageable pageable);
}

package com.blog.app.services.serviceImpl;

import com.blog.app.dto.CommentDto;
import com.blog.app.entities.Comment;
import com.blog.app.entities.Post;
import com.blog.app.entities.User;
import com.blog.app.exceptions.ResourceNotFoundException;
import com.blog.app.help.Helper;
import com.blog.app.help.PageableResponse;
import com.blog.app.repositories.CommentRepository;
import com.blog.app.repositories.PostRepository;
import com.blog.app.repositories.UserRepository;
import com.blog.app.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Override
    public CommentDto createComment(CommentDto commentDto, String userId, String postId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not fount with given id!!"));
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with given id!!"));
        Comment comment = mapper.map(commentDto, Comment.class);
        comment.setUser(user);
        comment.setPost(post);
        comment.setAddedDate(new Date());
        comment.setCommentId(UUID.randomUUID().toString());
        Comment save = commentRepository.save(comment);
        return mapper.map(save,CommentDto.class);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, String commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment not fount with given id!!"));
        comment.setComment(commentDto.getComment());
        Comment save = commentRepository.save(comment);
        return mapper.map(save,CommentDto.class);
    }

    @Override
    public void deleteComment(String commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment not fount with given id!!"));
        commentRepository.delete(comment);

    }

    @Override
    public PageableResponse<CommentDto> getCommentsByPost(String postId,int pageSize, int pageNumber, String sortDir, String sortBy) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with given id!!"));
        Sort sort=sortDir.equalsIgnoreCase("asc")?(Sort.by(sortBy).ascending()):(Sort.by(sortBy).descending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<Comment> page = commentRepository.findByPost(post,pageable);
        PageableResponse<CommentDto> response = Helper.getPageable(page, CommentDto.class);
        return response;
    }
}

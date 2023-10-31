package com.blog.app.services;

import com.blog.app.dto.CommentDto;
import com.blog.app.help.PageableResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, String userId, String postId);

    CommentDto updateComment(CommentDto commentDto, String commentId);

    void deleteComment(String commentId);

    PageableResponse<CommentDto> getCommentsByPost(String postId, int pageSize, int pageNumber, String sortDir, String sortBy);
}
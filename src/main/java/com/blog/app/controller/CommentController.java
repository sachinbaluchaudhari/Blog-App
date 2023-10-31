package com.blog.app.controller;

import com.blog.app.dto.ApiResponse;
import com.blog.app.dto.CommentDto;
import com.blog.app.dto.PostDto;
import com.blog.app.help.PageableResponse;
import com.blog.app.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/post/{postId}/user/{userId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
                                                    @PathVariable String postId,
                                                    @PathVariable String userId)
    {
        CommentDto comment = commentService.createComment(commentDto, userId, postId);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto,
                                                    @PathVariable String commentId)
    {
        CommentDto comment = commentService.updateComment(commentDto, commentId);
        return ResponseEntity.ok(comment);
    }
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable String commentId)
    {
        commentService.deleteComment(commentId);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("comment successfully deleted!")
                .status(HttpStatus.NO_CONTENT)
                .success(true)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/post/{postId}")
    public ResponseEntity<PageableResponse> getCommentsByPost(@PathVariable String postId,
                                                        @RequestParam(name = "pageNumber",defaultValue ="0",required = false) String pageNumber,
                                                       @RequestParam(name = "pageSize",defaultValue = "10",required = false) String pageSize,
                                                       @RequestParam(name = "sortBy",defaultValue = "addedDate",required = false) String sortBy,
                                                       @RequestParam(name = "sortDir",defaultValue = "asc",required = false) String sortDir)
    {
        PageableResponse<CommentDto> response =  commentService.getCommentsByPost(postId,Integer.parseInt(pageSize), Integer.parseInt(pageNumber), sortDir, sortBy);
        return ResponseEntity.ok(response);
    }

}

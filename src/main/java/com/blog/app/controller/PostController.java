package com.blog.app.controller;

import com.blog.app.dto.PostDto;
import com.blog.app.entities.Post;
import com.blog.app.help.PageableResponse;
import com.blog.app.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable String userId,
                                              @PathVariable String categoryId)
    {
        PostDto post = postService.createPost(postDto, categoryId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }
    @PutMapping("/{postId}")
    public  ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,
                                               @PathVariable String postId)
    {
        PostDto updated = postService.updatePost(postDto, postId);
        return ResponseEntity.ok(updated);
    }
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable String postId)
    {
        Post post = postService.getPost(postId);
        PostDto postDto = new ModelMapper().map(post, PostDto.class);
        return ResponseEntity.ok(postDto);
    }
    @GetMapping()
    public ResponseEntity<PageableResponse> getAllPost(@RequestParam(name = "pageNumber",defaultValue ="0",required = false) String pageNumber,
                                                       @RequestParam(name = "pageSize",defaultValue = "10",required = false) String pageSize,
                                                       @RequestParam(name = "sortBy",defaultValue = "title",required = false) String sortBy,
                                                       @RequestParam(name = "sortDir",defaultValue = "asc",required = false) String sortDir)
    {
        PageableResponse<PostDto> response = postService.getAllPost(Integer.parseInt(pageSize), Integer.parseInt(pageNumber), sortDir, sortBy);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<PageableResponse> getPostByUser(@PathVariable String userId,
                                                        @RequestParam(name = "pageNumber",defaultValue ="0",required = false) String pageNumber,
                                                       @RequestParam(name = "pageSize",defaultValue = "10",required = false) String pageSize,
                                                       @RequestParam(name = "sortBy",defaultValue = "title",required = false) String sortBy,
                                                       @RequestParam(name = "sortDir",defaultValue = "asc",required = false) String sortDir)
    {
        PageableResponse<PostDto> response = postService.getPostByUser(userId,Integer.parseInt(pageSize), Integer.parseInt(pageNumber), sortDir, sortBy);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<PageableResponse> getPostByCategory(@PathVariable String categoryId,
                                                          @RequestParam(name = "pageNumber",defaultValue ="0",required = false) String pageNumber,
                                                          @RequestParam(name = "pageSize",defaultValue = "10",required = false) String pageSize,
                                                          @RequestParam(name = "sortBy",defaultValue = "title",required = false) String sortBy,
                                                          @RequestParam(name = "sortDir",defaultValue = "asc",required = false) String sortDir)
    {
        PageableResponse<PostDto> response = postService.getPostByCategory(categoryId,Integer.parseInt(pageSize), Integer.parseInt(pageNumber), sortDir, sortBy);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{postId}/category/{categoryId}")
    public  ResponseEntity<PostDto> updatePostCategory(@PathVariable String postId,
                                               @PathVariable String categoryId)
    {
        PostDto updated = postService.updatePostCategory(postId,categoryId);
        return ResponseEntity.ok(updated);
    }

}

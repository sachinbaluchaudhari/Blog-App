package com.blog.app.services;

import com.blog.app.dto.CategoryDto;
import com.blog.app.dto.PostDto;
import com.blog.app.entities.Post;
import com.blog.app.help.PageableResponse;

public interface PostService {
    PostDto createPost(PostDto postDto,String categoryId,String userId);
    PostDto updatePost(PostDto postDto,String postId);
    Post getPost(String postId);
    PageableResponse<PostDto> getAllPost(int pageSize, int pageNumber, String sortDir, String sortBy);
    void deletePost(String postId);
    PageableResponse<PostDto> getPostByUser(String userId,int pageSize, int pageNumber, String sortDir, String sortBy);
    PageableResponse<PostDto> getPostByCategory(String userId,int pageSize, int pageNumber, String sortDir, String sortBy);
    PostDto updatePostCategory(String postId,String categoryId);

}

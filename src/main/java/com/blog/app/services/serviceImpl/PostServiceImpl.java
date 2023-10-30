package com.blog.app.services.serviceImpl;

import com.blog.app.dto.CategoryDto;
import com.blog.app.dto.PostDto;
import com.blog.app.entities.Category;
import com.blog.app.entities.Post;
import com.blog.app.entities.User;
import com.blog.app.exceptions.ResourceNotFoundException;
import com.blog.app.help.Helper;
import com.blog.app.help.PageableResponse;
import com.blog.app.repositories.CategoryRepository;
import com.blog.app.repositories.PostRepository;
import com.blog.app.repositories.UserRepository;
import com.blog.app.services.PostService;
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
public class PostServiceImpl implements PostService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public PostDto createPost(PostDto postDto,String categoryId,String userId){
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found with givrn id!"));
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not fount with given email!!"));
        Post post = mapper.map(postDto, Post.class);
        post.setPostId(UUID.randomUUID().toString());
        post.setUser(user);
        post.setCategory(category);
        post.setAddedDate(new Date());
        Post save = postRepository.save(post);
        return mapper.map(save,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, String postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with given id!!"));
        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());
        Post save = postRepository.save(post);
        return mapper.map(post,PostDto.class);
    }

    @Override
    public PostDto getPost(String postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with given id!!"));
        return mapper.map(post,PostDto.class);
    }

    @Override
    public PageableResponse<PostDto> getAllPost(int pageSize, int pageNumber, String sortDir, String sortBy) {
        Sort sort=sortDir.equalsIgnoreCase("asc")?(Sort.by(sortBy).ascending()):(Sort.by(sortBy).descending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> page = postRepository.findAll(pageable);
        PageableResponse<PostDto> response = Helper.getPageable(page, PostDto.class);

        return response;
    }

    @Override
    public void deletePost(String postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with given id!!"));
        postRepository.delete(post);
    }

    @Override
    public PageableResponse<PostDto> getPostByUser(String userId,int pageSize, int pageNumber, String sortDir, String sortBy) {
        Sort sort=sortDir.equalsIgnoreCase("asc")?(Sort.by(sortBy).ascending()):(Sort.by(sortBy).descending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not fount with given email!!"));
        Page<Post> page = postRepository.findByUser(user,pageable);
        PageableResponse<PostDto> response = Helper.getPageable(page, PostDto.class);
        return response;
    }

    @Override
    public PageableResponse<PostDto> getPostByCategory(String categoryId,int pageSize, int pageNumber, String sortDir, String sortBy) {

        Sort sort=sortDir.equalsIgnoreCase("asc")?(Sort.by(sortBy).ascending()):(Sort.by(sortBy).descending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found with givrn id!"));
        Page<Post>  page = postRepository.findByCategory(category,pageable);
        PageableResponse<PostDto> response = Helper.getPageable(page, PostDto.class);

        return response;
    }

    @Override
    public PostDto updatePostCategory(String postId, String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found with givrn id!"));
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with given id!!"));
        post.setCategory(category);
        Post save = postRepository.save(post);
        return mapper.map(save,PostDto.class);
    }
}

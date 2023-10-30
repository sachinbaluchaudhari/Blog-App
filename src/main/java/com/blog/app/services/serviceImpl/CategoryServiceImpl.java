package com.blog.app.services.serviceImpl;

import com.blog.app.dto.CategoryDto;
import com.blog.app.entities.Category;
import com.blog.app.exceptions.ResourceNotFoundException;
import com.blog.app.help.Helper;
import com.blog.app.help.PageableResponse;
import com.blog.app.repositories.CategoryRepository;
import com.blog.app.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        categoryDto.setCategoryId(UUID.randomUUID().toString());
        Category category = mapper.map(categoryDto, Category.class);
        Category saved = categoryRepository.save(category);
        return mapper.map(saved,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found with givrn id!"));
        category.setTitle(categoryDto.getTitle());
        Category updatedCategory = categoryRepository.save(category);
        return mapper.map(updatedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found with givrn id!"));

        return mapper.map(category,CategoryDto.class);
    }

    @Override
    public PageableResponse<CategoryDto> getAllCategory(int pageSize, int pageNumber, String sortDir, String sortBy) {
        Sort sort=sortDir.equalsIgnoreCase("asc")?(Sort.by(sortBy).ascending()):(Sort.by(sortBy).descending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<Category> page = categoryRepository.findAll(pageable);

        return Helper.getPageable(page,CategoryDto.class);
    }

    @Override
    public void deleteCategory(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found with given id!"));
        categoryRepository.delete(category);
    }
}

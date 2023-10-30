package com.blog.app.services;

import com.blog.app.dto.ApiResponse;
import com.blog.app.dto.CategoryDto;
import com.blog.app.help.PageableResponse;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto,String categoryId);
    CategoryDto getCategory(String categoryId);
    PageableResponse<CategoryDto> getAllCategory(int pageSize, int pageNumber, String sortDir, String sortBy);
    void deleteCategory(String categoryId);
}

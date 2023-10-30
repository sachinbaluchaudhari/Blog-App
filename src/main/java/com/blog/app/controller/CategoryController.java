package com.blog.app.controller;

import com.blog.app.dto.ApiResponse;
import com.blog.app.dto.CategoryDto;
import com.blog.app.dto.UserDto;
import com.blog.app.help.PageableResponse;
import com.blog.app.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto category = categoryService.createCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,
                                                      @PathVariable String categoryId)
    {
        CategoryDto category = categoryService.updateCategory(categoryDto, categoryId);
        return ResponseEntity.ok(category);

    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable String categoryId)
    {
        CategoryDto category = categoryService.getCategory(categoryId);
        return ResponseEntity.ok(category);
    }
    @GetMapping
    public ResponseEntity<PageableResponse<CategoryDto>> getAllCategory(@RequestParam(name = "pageNumber",defaultValue ="0",required = false) String pageNumber,
                                                                @RequestParam(name = "pageSize",defaultValue = "10",required = false) String pageSize,
                                                                @RequestParam(name = "sortBy",defaultValue = "title",required = false) String sortBy,
                                                                @RequestParam(name = "sortDir",defaultValue = "asc",required = false) String sortDir)
    {
        PageableResponse<CategoryDto> response =  categoryService.getAllCategory(Integer.parseInt(pageSize), Integer.parseInt(pageNumber), sortDir, sortBy);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable String categoryId)
    {
        categoryService.deleteCategory(categoryId);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Category deleted!")
                .success(true)
                .status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}

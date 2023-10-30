package com.blog.app.dto;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    private String categoryId;
    private String title;
}

package com.blog.app.services;

import com.blog.app.dto.UserDto;
import com.blog.app.help.PageableResponse;

import java.util.List;

public interface UserService {
    UserDto saveUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,String userId);
    UserDto getUser(String userId);
    PageableResponse<UserDto> getAllUser(int pageSize, int pageNumber, String sortDir, String sortBy);
    UserDto getByEmail(String email);
}

package com.blog.app.controller;

import com.blog.app.dto.UserDto;
import com.blog.app.help.PageableResponse;
import com.blog.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping()
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto)
    {
        UserDto saveUser = userService.saveUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,
                                              @PathVariable String userId)
    {
        UserDto updateUser = userService.updateUser(userDto, userId);
        return ResponseEntity.ok(updateUser);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable String userId)
    {
        UserDto userDto = userService.getUser(userId);
        return ResponseEntity.ok(userDto);
    }
    @GetMapping()
    public ResponseEntity<PageableResponse<UserDto>> getAllUser(@RequestParam(name = "pageNumber",defaultValue ="0",required = false) String pageNumber,
                                                                @RequestParam(name = "pageSize",defaultValue = "10",required = false) String pageSize,
                                                                @RequestParam(name = "sortBy",defaultValue = "name",required = false) String sortBy,
                                                                @RequestParam(name = "sortDir",defaultValue = "asc",required = false) String sortDir)
    {
        PageableResponse<UserDto> response = userService.getAllUser(Integer.parseInt(pageSize), Integer.parseInt(pageNumber), sortDir, sortBy);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getByEmail(@PathVariable String email)
    {
        UserDto userDto = userService.getByEmail(email);
        return ResponseEntity.ok(userDto);
    }
}

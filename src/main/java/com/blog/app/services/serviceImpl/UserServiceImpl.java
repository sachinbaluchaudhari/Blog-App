package com.blog.app.services.serviceImpl;

import com.blog.app.dto.UserDto;
import com.blog.app.entities.Role;
import com.blog.app.entities.User;
import com.blog.app.exceptions.ResourceNotFoundException;
import com.blog.app.help.Helper;
import com.blog.app.help.PageableResponse;
import com.blog.app.repositories.RoleRepository;
import com.blog.app.repositories.UserRepository;
import com.blog.app.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Value("${normal.user.id}")
    private String normalId;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public UserDto saveUser(UserDto userDto) {
        String userId = UUID.randomUUID().toString();
        userDto.setUserId(userId);
        User user = mapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findById(normalId).get();
        user.getRoles().add(role);
        User savedUser = userRepository.save(user);

        return mapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with given id!!"));
        user.setAbout(userDto.getAbout());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        User updatedUser = userRepository.save(user);
        return mapper.map(updatedUser, UserDto.class);
    }

    @Override
    public UserDto getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not fount with given id!!"));

        return mapper.map(user, UserDto.class);
    }

    @Override
    public PageableResponse<UserDto> getAllUser(int pageSize, int pageNumber, String sortDir, String sortBy) {
        Sort sort=sortDir.equalsIgnoreCase("asc")?(Sort.by(sortBy).ascending()):(Sort.by(sortBy).descending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<User> page = userRepository.findAll(pageable);

        return Helper.getPageable(page,UserDto.class);
    }

    @Override
    public UserDto getByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not fount with given email!!"));

        return mapper.map(user,UserDto.class);
    }
}

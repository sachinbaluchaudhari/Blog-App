package com.blog.app.controller;

import com.blog.app.dto.JwtRequest;
import com.blog.app.dto.JwtResponse;
import com.blog.app.dto.UserDto;
import com.blog.app.exceptions.ResourceNotFoundException;
import com.blog.app.jwtSecurity.JwtHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private ModelMapper mapper;
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest)
    {
        this.doAuthenticate(jwtRequest.getUserName(),jwtRequest.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUserName());
        String token = jwtHelper.generateToken(userDetails);
        JwtResponse jwtResponse = JwtResponse.builder()
                .jwtToken(token)
                .user(mapper.map(userDetails, UserDto.class))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(jwtResponse);
    }

    private void doAuthenticate(String userName, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userName,password);
        try{
            manager.authenticate(usernamePasswordAuthenticationToken);
        }catch (BadCredentialsException e)
        {
            throw new ResourceNotFoundException("UserName and password invalid!!");
        }
    }
}

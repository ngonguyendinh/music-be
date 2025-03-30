package com.example.demo.controller;

import com.example.demo.config.CustomUserDetailService;
import com.example.demo.config.jwt.JwtProvider;
import com.example.demo.entity.user.User;
import com.example.demo.entity.user.UserDto;
import com.example.demo.exception.UserException;
import com.example.demo.form.FormCreateUser;
import com.example.demo.form.LoginRequest;
import com.example.demo.repository.UserRepository;
import com.example.demo.response.AuthResponse;
import com.example.demo.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {
    private CustomUserDetailService customUserDetailService;
    private PasswordEncoder passwordEncoder;
    private ModelMapper mapper;
    private UserService userService;
    private UserRepository userRepository;
    @PostMapping("/register")
    public ResponseEntity<String> create  (@RequestBody FormCreateUser formCreateUser) throws Exception {
        if (userRepository.findByEmail(formCreateUser.getEmail()).isPresent()) {
            throw new Exception("Đã tồn tại email này, vui lòng sử dụng email khác");
        }
        mapper.map(userService.create(formCreateUser),UserDto.class);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest login){
        Authentication authentication = authenticate(login.getEmail(),login.getPassword());
        String token = JwtProvider.generateToken(authentication);
        AuthResponse response = new AuthResponse("login success",token);
        return response;
    }
    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customUserDetailService.loadUserByUsername(email);
        if (userDetails == null){
            throw new BadCredentialsException("invalid email");
        }
        if (!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("password not matched");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }

}

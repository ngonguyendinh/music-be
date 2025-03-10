package com.example.demo.controller;

import com.example.demo.entity.user.User;
import com.example.demo.entity.user.UserDto;
import com.example.demo.form.FormUpdateUser;
import com.example.demo.service.user.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;
    private ModelMapper mapper;
    @GetMapping
    public List<UserDto> findAll(){
        return userService.findAll().stream().map(user -> mapper.map(user,UserDto.class))
                .collect(Collectors.toList());
    }
    @GetMapping("/{userId}")
    public UserDto findById(@PathVariable("userId") int id){
        return mapper.map(userService.findById(id),UserDto.class);
    }
    @PutMapping("/profile")
    public UserDto update(@RequestBody FormUpdateUser form, @RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);
        return mapper.map(userService.update(form,user),UserDto.class);
    }
    @GetMapping("/search")
    public List<UserDto> searchUser(@RequestParam("query") String query){

        return userService.searchUser(query).stream().map(user -> mapper.map(user,UserDto.class))
                .collect(Collectors.toList());
    }
    @GetMapping("/profile")
    public UserDto getUserByToken(@RequestHeader("Authorization") String jwt){
        return mapper.map(userService.findUserByJwt(jwt),UserDto.class);
    }
    @PutMapping("/follow/{idfollower}")
    public UserDto follow(@RequestHeader("Authorization") String jwt, @PathVariable("idfollower") int idFollower) {
        User user = userService.findUserByJwt(jwt);
        return  mapper.map(userService.followUser(user.getId(), idFollower),UserDto.class);
    }
}

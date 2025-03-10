package com.example.demo.entity.user;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class UserDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Level level;
    private String avatar;
    private String gender;
    private String background;
    private Set<Integer> follower = new HashSet<>();
    private  Set<Integer> following = new HashSet<>();
}

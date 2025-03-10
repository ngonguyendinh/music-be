package com.example.demo.entity.user;

import com.example.demo.entity.comment.Comment;
import com.example.demo.entity.song.Song;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private String avatar;
    private String gender;
    private String background;
    private Set<Integer> follower = new HashSet<>();
    private  Set<Integer> following = new HashSet<>();

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    @OneToOne
    private ForgotPassword forgotPassword;
    @ManyToMany
    private Set<Song> likedSongs = new HashSet<>();
    private Level level;
    public void setRole(String role){
        this.role = Role.valueOf(role);
    }
    public  void setLevel(String level){
        this.level = Level.valueOf(level);
    }
}

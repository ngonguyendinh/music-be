package com.example.demo.entity.song;

import com.example.demo.entity.genre.Genre;
import com.example.demo.entity.user.User;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
@Data
public class SongDto {
    private int id;
    private String title;
    private String artist;
    private String albumName;
    private String genreName;
    private String coverImage;
    private String audioUrl;
    private int duration;//tgian bài hát
    private int playCount;//lượt nghe
    private Set<User> likedByUsers = new HashSet<>();
    private User uploadedBy;

}

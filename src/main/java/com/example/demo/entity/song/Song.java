package com.example.demo.entity.song;

import com.example.demo.entity.album.Album;
import com.example.demo.entity.genre.Genre;
import com.example.demo.entity.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="music")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String artist;
    private String coverImage;
    private String audioUrl;
    private int duration;
    private int playCount;

    @ManyToOne
    private Album album;

    @ManyToOne
    private Genre genre;

    @ManyToMany(mappedBy = "likedSongs")
    private Set<User> likedByUsers = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User uploadedBy;

}

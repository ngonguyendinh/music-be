package com.example.demo.entity.playlist;

import com.example.demo.entity.song.Song;
import com.example.demo.entity.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String coverImage;

    @ManyToOne
    private User owner; // Người tạo playlist

    @ManyToMany
    private List<Song> songs = new ArrayList<>();
}

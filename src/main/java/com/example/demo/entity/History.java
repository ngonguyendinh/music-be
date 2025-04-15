package com.example.demo.entity;

import com.example.demo.entity.song.Song;
import com.example.demo.entity.user.User;

import java.time.LocalDateTime;

public class History {
    private int id;
    private User user;
    private Song song;
    private LocalDateTime createdAt;
}

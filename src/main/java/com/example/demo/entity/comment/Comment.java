package com.example.demo.entity.comment;

import com.example.demo.entity.song.Song;
import com.example.demo.entity.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne
    private User user;

    @ManyToOne
    private Song song;

    private LocalDateTime createdAt;
}

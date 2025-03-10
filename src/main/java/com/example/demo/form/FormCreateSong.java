package com.example.demo.form;

import com.example.demo.entity.genre.Genre;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class FormCreateSong {
    private String title;
    private String artist;
    private String album;
    private String genre;
    private String coverImage;
    private String audioUrl;
}

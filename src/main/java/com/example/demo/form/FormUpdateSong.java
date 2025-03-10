package com.example.demo.form;

import com.example.demo.entity.genre.Genre;
import lombok.Data;

@Data

public class FormUpdateSong {
    private String title;
    private String artist;
    private String album;
    private String genre;
    private String coverImage;
    private String audioUrl;
    private int duration;
    private int playCount;
}

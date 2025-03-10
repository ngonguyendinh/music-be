package com.example.demo.entity.album;

import com.example.demo.entity.song.Song;
import com.example.demo.entity.song.SongDto;
import lombok.Data;

import java.util.List;
@Data
public class AlbumDto {
    private int id;
    private String name;
    private List<SongDto> songs;
}

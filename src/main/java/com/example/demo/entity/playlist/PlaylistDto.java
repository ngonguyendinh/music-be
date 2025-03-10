package com.example.demo.entity.playlist;

import com.example.demo.entity.song.SongDto;
import com.example.demo.entity.user.UserDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class PlaylistDto {
    private Long id;
    private String name;
    private String description;
    private String coverImage;
    private UserDto owner;
    private List<SongDto> songs = new ArrayList<>();
}

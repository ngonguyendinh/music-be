package com.example.demo.controller;

import com.example.demo.entity.album.Album;
import com.example.demo.entity.album.AlbumDto;
import com.example.demo.form.FormSearchAlbum;
import com.example.demo.service.album.AlbumService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/albums")
public class AlbumController {
    private AlbumService albumService;
    private ModelMapper mapper;
    @PostMapping
    public AlbumDto createAlbum(@RequestBody Album album){
        return mapper.map(albumService.create(album), AlbumDto.class);
    }
    @GetMapping
    public AlbumDto findSongsAlbum(@RequestBody FormSearchAlbum album){
        return mapper.map(albumService.findAlbumByName(album.getName()), AlbumDto.class);
    }


}

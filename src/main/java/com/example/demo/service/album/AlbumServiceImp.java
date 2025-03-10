package com.example.demo.service.album;

import com.example.demo.entity.album.Album;
import com.example.demo.repository.AlbumRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AlbumServiceImp implements AlbumService{
    private AlbumRepository albumRepository;
    @Override
    public Album findAlbumByName(String name) {
        return albumRepository.findByName(name);
    }

    @Override
    public Album create(Album album) {
        return albumRepository.save(album);
    }
}

package com.example.demo.service.album;

import com.example.demo.entity.album.Album;

public interface AlbumService {
    Album findAlbumByName(String name);
    Album create(Album album);
}

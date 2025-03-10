package com.example.demo.repository;

import com.example.demo.entity.album.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album , Integer> {
    Album findByName(String name);
}

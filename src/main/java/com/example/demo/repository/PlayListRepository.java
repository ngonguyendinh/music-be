package com.example.demo.repository;

import com.example.demo.entity.playlist.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayListRepository extends JpaRepository<Playlist,Integer> {
}

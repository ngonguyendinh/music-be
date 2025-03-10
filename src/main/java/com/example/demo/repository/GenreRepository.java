package com.example.demo.repository;

import com.example.demo.entity.genre.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre,Integer> {
    Genre findByName(String name);
}

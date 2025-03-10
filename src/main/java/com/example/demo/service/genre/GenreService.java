package com.example.demo.service.genre;

import com.example.demo.entity.genre.Genre;

public interface GenreService {
    Genre create(Genre genre);
    Genre findByName(String name);
}

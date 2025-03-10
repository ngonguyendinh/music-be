package com.example.demo.service.genre;

import com.example.demo.entity.genre.Genre;
import com.example.demo.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GenreServiceImp implements GenreService{
    private GenreRepository genreRepository;

    @Override
    public Genre create(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Genre findByName(String name) {
        return genreRepository.findByName(name);
    }
}

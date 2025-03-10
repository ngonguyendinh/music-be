package com.example.demo.controller;

import com.example.demo.entity.genre.Genre;
import com.example.demo.entity.genre.GenreDto;
import com.example.demo.form.FormSearchGenre;
import com.example.demo.service.genre.GenreService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/genres")
@AllArgsConstructor
public class GenreController {
    private GenreService genreService;
    private ModelMapper mapper;
    @PostMapping
    public GenreDto createGenre(@RequestBody Genre genre){
        return mapper.map(genreService.create(genre), GenreDto.class);
    }
    @GetMapping
    public GenreDto findByGenre(@RequestBody FormSearchGenre genre){
        return mapper.map(genreService.findByName(genre.getName()), GenreDto.class);
    }
}

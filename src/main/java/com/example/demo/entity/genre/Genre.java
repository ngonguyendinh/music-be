package com.example.demo.entity.genre;

import com.example.demo.entity.song.Song;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "genre")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Song> songs = new ArrayList<>();
}

package com.example.demo.repository;

import com.example.demo.entity.song.Song;
import com.example.demo.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SongRepository extends JpaRepository<Song,Integer> {

    List<Song> findSongByGenreId(int genreId);
    List<Song> findByUploadedBy(User user);
    @Query("SELECT s FROM Song s " +
            "WHERE LOWER(s.title) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(s.artist) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(s.album) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(s.genre.name) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(s.uploadedBy.email) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Song> searchSongs(@Param("query") String query);

}

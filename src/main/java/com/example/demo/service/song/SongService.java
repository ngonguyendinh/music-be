package com.example.demo.service.song;

import com.example.demo.entity.song.Song;
import com.example.demo.entity.user.User;
import com.example.demo.form.FormCreateSong;
import com.example.demo.form.FormUpdateSong;

import java.util.List;
import java.util.Set;

public interface SongService {
    Song addSong(int userId, FormCreateSong form);
    Song updateSong(int userId, int songId, FormUpdateSong form);
    void deleteSong(int userId, int songId);
    Song getSongById(int songId);
    List<Song> getAllSongs();
    List<Song> getSongByUser(int userId);

    List<Song> searchSongs(String keyword);
    Song increasePlayCount(int songId);
    Song likeSong(int userId, int songId);

}

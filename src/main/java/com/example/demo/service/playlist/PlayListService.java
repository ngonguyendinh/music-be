package com.example.demo.service.playlist;

import com.example.demo.entity.playlist.Playlist;
import com.example.demo.form.FormcreatePlayList;

import java.util.List;

public interface PlayListService {
    Playlist createPlayList(int userId, FormcreatePlayList form);
    List<Playlist> findAll();
    Playlist addSongtoPlaylist(int songId, int playlistId);
    Playlist findPlayListId(int playlistId);
    void deletePlayList(int userId,int playlistId);
}

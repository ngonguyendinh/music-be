package com.example.demo.service.playlist;

import com.example.demo.entity.playlist.Playlist;
import com.example.demo.entity.user.User;
import com.example.demo.form.FormcreatePlayList;
import com.example.demo.repository.PlayListRepository;
import com.example.demo.service.song.SongService;
import com.example.demo.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlayListServiceImp implements PlayListService{
    private UserService userService;
    private PlayListRepository playListRepository;
    private SongService songService;
    @Override
    public Playlist createPlayList(int userId, FormcreatePlayList form) {

        Playlist playList = new Playlist();
        User user = userService.findById(userId);
        playList.setOwner(user);
        return playListRepository.save(playList);
    }

    @Override
    public List<Playlist> findAll() {
        return playListRepository.findAll();
    }

    @Override
    public Playlist addSongtoPlaylist(int songId, int playlistId) {
        Playlist playList = playListRepository.findById(playlistId).get();
        playList.getSongs().add(songService.getSongById(songId));

        return playListRepository.save(playList);
    }

    @Override
    public Playlist findPlayListId(int playlistId) {
        return playListRepository.findById(playlistId).get();
    }


    @Override
    public void deletePlayList(int userId,int playlistId) {
        Playlist playlist= playListRepository.findById(playlistId).get();
        User user = userService.findById(userId);
        if (user.getId()!= playlist.getOwner().getId()){
            throw  new RuntimeException("You do not have permission to delete ");
        }
        playListRepository.deleteById(playlistId);
    }
}

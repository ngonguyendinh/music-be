package com.example.demo.service.song;

import com.example.demo.entity.song.Song;
import com.example.demo.entity.user.User;
import com.example.demo.form.FormCreateSong;
import com.example.demo.form.FormUpdateSong;
import com.example.demo.repository.SongRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.album.AlbumService;
import com.example.demo.service.genre.GenreService;
import com.example.demo.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class SongServiceImp implements SongService {
    private UserService userService;
    private SongRepository songRepository;
    private UserRepository userRepository;
    private AlbumService albumService;
    private GenreService genreService;

    @Override
    public Song addSong(int userId, FormCreateSong form) {
        User user = userService.findById(userId);

        if (user == null){
            throw  new RuntimeException("not found user");
        }
        Song song = new Song();
        song.setTitle(form.getTitle());
        song.setArtist(form.getArtist());
        song.setAlbum(albumService.findAlbumByName(form.getAlbum()));
        song.setGenre(genreService.findByName(form.getGenre()));
        song.setArtist(form.getArtist());
        song.setAudioUrl(form.getAudioUrl());
        song.setCoverImage(form.getCoverImage());
        song.setUploadedBy(user);
        return songRepository.save(song);
    }

    @Override
    public Song updateSong(int userId, int songId, FormUpdateSong form) {
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("Song not found!"));
        User user = userService.findById(userId);

        if (userId!=song.getUploadedBy().getId()) {
            throw new RuntimeException("You do not have permission to update this song!");
        }

        song.setTitle(form.getTitle());
        song.setArtist(form.getArtist());
        song.setAlbum(albumService.findAlbumByName(form.getAlbum()));
        song.setCoverImage(form.getCoverImage());
        song.setAudioUrl(form.getAudioUrl());
        song.setDuration(form.getDuration());
        song.setGenre(genreService.findByName(form.getGenre()));

        return songRepository.save(song);
    }

    @Override
    public void deleteSong(int userId, int songId) {
        Song song = songRepository.findById(songId).get();
        if (song.getUploadedBy().getId()!=userId){
            throw new RuntimeException("You do not have permission to delete this song!");
        }
        songRepository.deleteById(songId);
    }

    @Override
    public Song getSongById(int songId) {

        return songRepository.findById(songId).get();
    }

    @Override
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    @Override
    public List<Song> getSongByUser(int userId) {
        User user = userService.findById(userId);

        return songRepository.findByUploadedBy(user);
    }


    @Override
    public List<Song> searchSongs(String keyword) {
        return songRepository.searchSongs(keyword);
    }

    @Override
    public Song increasePlayCount(int songId) {
        Song song = getSongById(songId);
        song.setPlayCount(song.getPlayCount() + 1);
        return songRepository.save(song);
    }

    @Override
    public Song likeSong(int userId, int songId) {
        Song song = songRepository.findById(songId).get();
        User user = userRepository.findById(userId).get();
        if (song.getLikedByUsers().contains(user)){
            song.getLikedByUsers().remove(user);

        }
        song.getLikedByUsers().add(user);
       return songRepository.save(song);

    }


}

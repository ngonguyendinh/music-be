package com.example.demo.controller;

import com.example.demo.entity.playlist.Playlist;
import com.example.demo.entity.playlist.PlaylistDto;
import com.example.demo.entity.song.SongDto;
import com.example.demo.entity.user.User;
import com.example.demo.form.FormcreatePlayList;
import com.example.demo.service.playlist.PlayListService;
import com.example.demo.service.user.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/playlist")
@AllArgsConstructor
public class PlaylistController {
    private PlayListService playListService;
    private UserService userService;
    private ModelMapper mapper;
    @PostMapping
    public PlaylistDto createPlaylist(@RequestBody FormcreatePlayList form , @RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);
        Playlist p =playListService.createPlayList(user.getId(), form);
        return mapper.map(p, PlaylistDto.class);
    }
    @PutMapping("/{playlistId}/song/{songId}")
    public PlaylistDto addSongtoPlaylist(@PathVariable("songId") int songId,@PathVariable("playlistId") int playlistId){
        Playlist playlist=playListService.addSongtoPlaylist(songId,playlistId);
        return mapper.map(playlist, PlaylistDto.class);
    }
    @GetMapping
    public List<PlaylistDto> findAll(){

        return playListService.findAll().stream().map(playlist -> mapper.map(playlist, PlaylistDto.class))
                .collect(Collectors.toList());
    }
    @GetMapping("/{playlistId}")
    public PlaylistDto findPlaylistById(@PathVariable("playlistId") int playlistId){
        return mapper.map(playListService.findPlayListId(playlistId), PlaylistDto.class);
    }
    @DeleteMapping
    public void deletePlaylist(@RequestHeader("Authorization") String jwt,@PathVariable("playlistId") int playlistId){
        User user = userService.findUserByJwt(jwt);
        playListService.deletePlayList(user.getId(), playlistId);
    }
}

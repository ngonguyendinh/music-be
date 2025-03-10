package com.example.demo.controller;

import com.example.demo.entity.notification.Notification;
import com.example.demo.entity.song.Song;
import com.example.demo.entity.song.SongDto;
import com.example.demo.entity.user.User;
import com.example.demo.form.FormCreateSong;
import com.example.demo.form.FormUpdateSong;
import com.example.demo.service.song.SongService;
import com.example.demo.service.user.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.repository.query.Param;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/songs")
@AllArgsConstructor
public class SongController {
    private SongService songService;
    private UserService userService;
    private ModelMapper mapper;
    private SimpMessagingTemplate messagingTemplate;
    @PostMapping
    public SongDto addSong(@RequestHeader("Authorization") String jwt , @RequestBody FormCreateSong form){
        User user = userService.findUserByJwt(jwt);
        Set<Integer> users = user.getFollowing();
        Song song = songService.addSong(user.getId(),form);
        for (Integer followingId : users) {
            messagingTemplate.convertAndSend( "/queue/notification-"+followingId.toString(),
            new Notification("Có bài nhạc mới được đăng!"));
        }
        return mapper.map(song,SongDto.class);
    }
    @PutMapping("/{songId}")
    public SongDto updateSong(@RequestHeader("Authorization") String jwt ,@PathVariable("songId")int songId,@RequestBody FormUpdateSong form){
        User user = userService.findUserByJwt(jwt);

        return mapper.map(songService.updateSong(user.getId(),songId,form),SongDto.class);
    }
    @PutMapping("/play/{songId}")
    public SongDto playCount(@PathVariable("songId") int songId){
        return mapper.map(songService.increasePlayCount(songId),SongDto.class);
    }
    @PutMapping("/like/{songId}")
    public SongDto likeSong(@PathVariable("songId")int songId, @RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);
        return mapper.map(songService.likeSong(user.getId(),songId),SongDto.class);
    }
    @GetMapping("/play/search")
    public List<SongDto> sreachSong(@RequestParam("query") String query){
        return songService.searchSongs(query).stream().map(song -> mapper.map(song,SongDto.class))
                .collect(Collectors.toList());
    }
    @GetMapping("/play/user/{userId}")
    public List<SongDto> getSongByUserUpload(@PathVariable("userId") int userId){
        return songService.getSongByUser(userId).stream().map(song -> mapper.map(song, SongDto.class))
                .collect(Collectors.toList());
    }
    @GetMapping("/play")
    public List<SongDto> findAll(){
        return songService.getAllSongs().stream().map(song -> mapper.map(song,SongDto.class))
                .collect(Collectors.toList());
    }
    @GetMapping("/song/{songId}")
    public SongDto findById(@PathVariable("songId") int songId){
        return mapper.map(songService.getSongById(songId),SongDto.class);
    }


}

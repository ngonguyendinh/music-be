package com.example.demo.service.comment;

import com.example.demo.entity.comment.Comment;
import com.example.demo.entity.song.Song;
import com.example.demo.entity.user.User;
import com.example.demo.form.FormCreateComment;
import com.example.demo.repository.CommentRepository;
import com.example.demo.service.song.SongService;
import com.example.demo.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CommentImp  implements CommentService{
    private CommentRepository commentRepository;
    private UserService userService;
    private SongService songService;
    @Override
    public Comment create(FormCreateComment form, int userId, int songId) {
        User user = userService.findById(userId);
        Song song = songService.getSongById(songId);
        Comment comment = new Comment();
        comment.setContent(form.getContent());
        comment.setUser(user);
        comment.setSong(song);
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(Comment comment, Long commentId) {
       Comment comment1 =  commentRepository.findById(commentId).get();
       comment1.setContent(comment.getContent());
       comment1.setCreatedAt(comment.getCreatedAt());
        return commentRepository.save(comment1);
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public List<Comment> findCommentBySong() {
        return null;
    }
}

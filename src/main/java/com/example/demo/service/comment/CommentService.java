package com.example.demo.service.comment;

import com.example.demo.entity.comment.Comment;
import com.example.demo.entity.comment.CommentDto;
import com.example.demo.form.FormCreateComment;

import java.util.List;

public interface CommentService {
    Comment create(FormCreateComment form , int userId , int songId) ;
    Comment update(Comment comment, Long commentId);
    List<Comment> findAll();
    List<Comment> findCommentBySong();


}

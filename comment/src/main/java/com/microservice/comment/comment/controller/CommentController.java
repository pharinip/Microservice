package com.microservice.comment.comment.controller;

import com.microservice.comment.comment.entity.Comment;
import com.microservice.comment.comment.payload.CommentDto;
import com.microservice.comment.comment.service.CommentService;
import com.microservice.comment.comment.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {


    @Autowired
    private CommentServiceImpl commentService;


//http://localhost:8082/api/comments
    @PostMapping
    public Comment  createComment(@RequestBody Comment comment){

        Comment comment1 = commentService.saveComment(comment);
        // return new ResponseEntity<>(commentDto1, HttpStatus.OK);
        return comment1;
    }

    //http://localhost:8082/api/comments/
    @GetMapping("/{postId}")
    public List<Comment> getPostByCommentId(@PathVariable String postId){
        List<Comment> comments=commentService.getPostByCommentId(postId);
        return comments;
    }


}

package com.microservice.post.controller;

import com.microservice.post.dto.Comment;
import com.microservice.post.dto.PostCommentDto;
import com.microservice.post.dto.PostDto;
import com.microservice.post.entity.Post;
import com.microservice.post.service.PostService;
import com.microservice.post.service.impl.PostServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostServiceImpl postServiceImpl;

    @PostMapping
    public ResponseEntity<PostDto> savePost(@RequestBody PostDto postDto) {
        PostDto postDto1 = postServiceImpl.savePost(postDto);


        return new ResponseEntity<>(postDto1, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable String postId) {
        Post post = postServiceImpl.getPostById(postId);
        return post;

    }

    //http://localhost:8081/api/posts/?postId=
    @GetMapping
    //@CircuitBreaker(name = "commentBreaker", fallbackMethod = "commentFallback")
    @CircuitBreaker(name = "commentService", fallbackMethod = "getPostWithCommentsFallback")
    public ResponseEntity<PostCommentDto> getPostWithComments(@RequestParam String postId) {
        PostCommentDto dto = postServiceImpl.getPostWithComments(postId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    public ResponseEntity<PostCommentDto> getPostWithCommentsFallback(String postId, Exception ex) {
        System.out.println("Fallback is executed because service is down:" + ex.getMessage());

        ex.printStackTrace();
        PostCommentDto dto = new PostCommentDto();
        //set some dummy values because it has to return POstCommentDto so set some dummy values
        dto.setPostId("12345");
        dto.setTitle("Service is down");
        dto.setContent("service is down..");
        dto.setDescription("service is down...");
        Comment c = new Comment();
        c.setCommentId("123");
        c.setPostId("123");
        c.setBody("server is down");
        c.setName("server is down");
        c.setEmail("server is down");
        List<Comment> list = new ArrayList<>();
        list.add(c);

        dto.setComments(list);

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }



}
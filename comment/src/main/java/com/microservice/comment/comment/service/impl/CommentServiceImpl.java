package com.microservice.comment.comment.service.impl;

import com.microservice.comment.comment.config.RestTemplateConfig;
import com.microservice.comment.comment.payload.CommentDto;
import com.microservice.comment.comment.payload.Post;
import com.microservice.comment.comment.repository.CommentRepository;
import com.microservice.comment.comment.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.comment.comment.entity.Comment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class CommentServiceImpl {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RestTemplateConfig restTemplate;

    @Autowired
    private ModelMapper modelMapper;


    public Comment saveComment(Comment comment) {
    Post post=restTemplate.getRestTemplate().getForObject("http://POST-SERVICE/api/posts/"+comment.getPostId(), Post.class);

        if( post != null){

           String commentId= UUID.randomUUID().toString();
           comment.setCommentId(commentId);
            Comment savedComment = commentRepository.save(comment);


            return savedComment;

        }else{
            return null;
        }


    }
    Comment mapToEntity(CommentDto commentDto){
        Comment comment=modelMapper.map(commentDto,Comment.class);
        return comment;

    }
    CommentDto mapToDto(Comment comment){
       CommentDto commentDto= modelMapper.map(comment,CommentDto.class);
       return commentDto;
    }


    public List<Comment> getPostByCommentId(String postId) {
        List<Comment> comments=commentRepository.findByPostId(postId);
        return comments;
    }
}

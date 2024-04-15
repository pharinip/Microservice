package com.microservice.comment.comment.service;

import com.microservice.comment.comment.payload.CommentDto;

public interface CommentService {

    CommentDto saveComment(CommentDto commentDto);
}

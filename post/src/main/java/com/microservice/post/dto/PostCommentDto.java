package com.microservice.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PostCommentDto {

    private String postId;
    private String title;
    private String description;
    private String content;
    List<Comment> comments;
}

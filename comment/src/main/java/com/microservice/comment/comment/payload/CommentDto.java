package com.microservice.comment.comment.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private String CommentId;
    private String name;
    private String email;
    private String body;
    private String postId;
    private String message;
}

package com.microservice.comment.comment.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;


@Entity
@Table(name="comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    private String commentId;

    private String name;
    private String email;
    private String body;
    private String postId;

}

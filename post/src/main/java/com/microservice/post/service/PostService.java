package com.microservice.post.service;

import com.microservice.post.dto.PostDto;
import com.microservice.post.entity.Post;

public interface PostService {

   PostDto savePost(PostDto postDto);

   PostDto getPostById(String postId);
}

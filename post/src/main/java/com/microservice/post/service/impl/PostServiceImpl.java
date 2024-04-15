package com.microservice.post.service.impl;

import com.microservice.post.config.RestTemplateConfig;
import com.microservice.post.dto.Comment;
import com.microservice.post.dto.PostCommentDto;
import com.microservice.post.dto.PostDto;
import com.microservice.post.entity.Post;
import com.microservice.post.repository.PostRepository;
import com.microservice.post.service.PostService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostServiceImpl {

    @Autowired
    private PostRepository postRepository;



    @Qualifier("postRestTemplate")
    private RestTemplateConfig restTemplate;

    @Autowired
    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, RestTemplateConfig restTemplate, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.restTemplate = restTemplate;
        this.modelMapper = modelMapper;
    }

    public PostDto savePost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        String postId = UUID.randomUUID().toString();
        post.setId(postId);
        Post savedPost = postRepository.save(post);

       PostDto postDto1=mapToDto(savedPost);
       postDto1.setMessage("Post saved sucessfully");

        return postDto1;
    }


    public Post getPostById(String postId) {
        Optional<Post> byId = postRepository.findById(postId);
        Post post1=byId.get();
        if(byId.isPresent()) {
            Post post = byId.get();

            return post;

        }else{
          System.out.println("Post not found");

        }
//       PostDto postDto= mapToDto(post);
//       postDto.setMessage("Post retrived sucessfully");
       return post1;

    }

    Post mapToEntity(PostDto postDto){
        Post post = modelMapper.map(postDto, Post.class);
        return post;

    }

    PostDto mapToDto(Post post){
        PostDto postDto=modelMapper.map(post,PostDto.class);
        return postDto;
    }

    public PostCommentDto getPostWithComments(String postId) {
        Post post = postRepository.findById(postId).get();
        List<Comment> comments = restTemplate.getRestTemplate().getForObject("http://COMMENT-SERVICE/api/comments/" + postId, List.class);
        PostCommentDto postCommentDto = maptoPostCommentDto(post);
       postCommentDto.setComments(comments);
        return postCommentDto ;

    }

    PostCommentDto maptoPostCommentDto(Post post){
        PostCommentDto dto1 = modelMapper.map(post, PostCommentDto.class);
        return dto1;
    }
}

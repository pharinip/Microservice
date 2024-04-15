package com.microservice.comment.comment.config;

import com.microservice.comment.comment.service.impl.CommentServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public ModelMapper getModelMapper(){
        return  new ModelMapper();
    }

//    @Bean
//    public CommentServiceImpl getCommentServiceImpl(){
//        return new CommentServiceImpl();
//    }


    @Bean("commentRestTemplate")
    @LoadBalanced
    public RestTemplate getRestTemplate(){

        return new RestTemplate();
    }

}

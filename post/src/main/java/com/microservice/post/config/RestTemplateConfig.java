package com.microservice.post.config;

import com.microservice.post.service.impl.PostServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public ModelMapper modelMapper(){

        return new ModelMapper();
    }


//    @Bean
//    public PostServiceImpl getPostServiceImpl(){
//        return new PostServiceImpl();
//    }

    @Bean("postRestTemplate")
    @LoadBalanced
    public RestTemplate getRestTemplate(){

        return new RestTemplate();
    }
            
}

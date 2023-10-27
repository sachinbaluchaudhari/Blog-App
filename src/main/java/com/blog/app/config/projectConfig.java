package com.blog.app.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class projectConfig {
    @Bean
    public ModelMapper mapper()
    {
        return new ModelMapper();
    }
}

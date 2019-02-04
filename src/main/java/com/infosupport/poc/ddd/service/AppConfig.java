package com.infosupport.poc.ddd.service;

import com.infosupport.poc.ddd.domain.service.ForwardDateService;
import com.infosupport.poc.ddd.domain.service.ForwardDateServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ForwardDateService forwardDateService() {
        return new ForwardDateServiceImpl();
    }

}

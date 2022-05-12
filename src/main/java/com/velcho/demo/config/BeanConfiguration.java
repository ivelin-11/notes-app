package com.velcho.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validation;
import javax.validation.Validator;


@Configuration
public class BeanConfiguration {


    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }


}

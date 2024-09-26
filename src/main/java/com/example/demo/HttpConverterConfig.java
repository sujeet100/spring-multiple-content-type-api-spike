package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

@Configuration
public class HttpConverterConfig {

    @Bean
    public HttpMessageConverter<CSVSerializable> createCsvHttpMessageConverter() {
        return new CSVHttpMessageConverter();
    }


}

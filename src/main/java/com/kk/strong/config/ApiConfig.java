package com.kk.strong.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(ApiConfigProperties.class)
public class ApiConfig {

    private final ApiConfigProperties apiConfigProperties;

    @Bean
    public WebClient mealApiClient() {
        return WebClient
                .builder()
                .baseUrl(apiConfigProperties.getMealApiUrl())
                .defaultHeaders(httpHeaders -> httpHeaders.set("X-RapidAPI-Key", apiConfigProperties.getXRapidApiKey()))
                .build();
    }
}

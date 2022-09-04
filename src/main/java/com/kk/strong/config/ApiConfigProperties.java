package com.kk.strong.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@AllArgsConstructor
@ConfigurationProperties("api")
public class ApiConfigProperties {

    private final String xRapidApiKey;
    private final String mealApiUrl;
}

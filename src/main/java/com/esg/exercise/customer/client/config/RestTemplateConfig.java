package com.esg.exercise.customer.client.config;

import java.time.Duration;

import com.esg.exercise.customer.client.config.ApiProxyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Autowired
    private ApiProxyProperties apiProxyProperties;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofMillis(3000))
                .setReadTimeout(Duration.ofMillis(3000))
                .additionalInterceptors(
                        (request, body, execution) -> {
                            return execution.execute(request, body);
                        })
                .build();
    }
}

package com.esg.exercise.customer.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "apiproxy")
@Getter
@Setter
public class ApiProxyProperties {
    private String url;
}
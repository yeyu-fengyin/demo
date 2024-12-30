package com.example.graphql.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * @Auth yeyu
 * @Date 2024/11/21
 */
@Configuration
@ConfigurationProperties(prefix = "test")
@Data
public class TestConfig {
    private Duration outTime;
}

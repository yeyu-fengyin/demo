package com.example.webSocket.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import kotlin.time.Duration

/**
 *
 *@Auth yeyu
 *@Date 2024/11/21
 *
 */
@ConfigurationProperties(prefix = "test")
@Configuration
class TestConfig {
    var outTime:Duration = Duration.ZERO
}
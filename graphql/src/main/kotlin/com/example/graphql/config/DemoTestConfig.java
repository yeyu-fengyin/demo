package com.example.graphql.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Auth yeyu
 * @Date 2024/11/21
 */
@Component
@Slf4j
public class DemoTestConfig {
    @Resource
    TestConfig testConfig;

    @PostConstruct
    public void init() {
        System.out.println(this.testConfig.getOutTime());
        log.info("日志打印");
    }
}

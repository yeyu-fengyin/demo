package com.example.common.bean

import com.example.common.annotation.RegisterListener
import com.example.common.domain.User
import com.example.common.event.MyEvent
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 *
 *@Auth yeyu
 *@Date 2024/9/29
 *
 **/
@Configuration
class BeanConfig {
    @Bean("user")
    fun createUser(): User {
        return User("yeyu", 25)
    }

    @RegisterListener
    fun myListener(myEvent: MyEvent) {
        println(myEvent.myField)
    }
}
package com.example.common.listener

import com.example.common.annotation.RegisterListener
import com.example.common.event.MyEvent
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import java.util.EventListener

/**
 *
 *@Auth yeyu
 *@Date 2024/9/30
 *
 **/

@Component
@RegisterListener
class MyListener:EventListener {

    fun handleEvent(event: MyEvent) {
        println(event.myField)
    }

}
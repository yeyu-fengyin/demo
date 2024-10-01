package com.example.common.event

import com.example.common.listener.MyListener
import org.springframework.context.annotation.Configuration

/**
 *
 *@Auth yeyu
 *@Date 2024/9/30
 *
 **/
@Configuration
class MyEventResoure {
    val myListeners : MutableList<MyListener> = mutableListOf()

    fun addListener(listener: MyListener) {
        myListeners.add(listener)
    }

    fun removeListener(listener: MyListener) {
        myListeners.remove(listener)
    }

    fun notifyListeners(event: MyEvent) {
        myListeners.forEach { it.handleEvent(event) }
    }
}
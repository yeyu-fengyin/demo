package com.example.common.scan

import com.example.common.annotation.RegisterListener
import com.example.common.application.SpringBootMyApplication
import com.example.common.domain.User
import com.example.common.event.MyEvent
import com.example.common.event.MyEventResoure
import com.example.common.listener.DefaultListener
import com.example.common.listener.MyListener
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner
import org.springframework.stereotype.Component

/**
 *
 *@Auth yeyu
 *@Date 2024/9/30
 *
 **/
@Component
class MyListenerScan {
    @Autowired
    lateinit var springBootMyApplication: SpringBootMyApplication

    @Autowired
    lateinit var myEventResoure: MyEventResoure
@PostConstruct
    fun init() {
    val beans = springBootMyApplication.getBeansWithAnnotation(RegisterListener::class.java)
    beans?.forEach{
        val myListener = it.value as MyListener
        myEventResoure.addListener(myListener)
    }

    val methodsWithAnnotation = springBootMyApplication.getMethodsWithAnnotation(RegisterListener::class.java)
    methodsWithAnnotation?.forEach{
        val defaultListener = DefaultListener().init(it.key,it.value)
        myEventResoure.addListener(defaultListener)
    }
    println(myEventResoure.myListeners.size)
    val myEvent = MyEvent()
    myEvent.myField = "Hello, world!"
    myEvent.commit()
    myEventResoure.notifyListeners(myEvent)
}
}
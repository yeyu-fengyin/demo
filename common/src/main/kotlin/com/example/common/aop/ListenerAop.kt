package com.example.common.aop

import com.example.common.event.MyEventResoure
import com.example.common.listener.MyListener
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

/**
 *
 *@Auth yeyu
 *@Date 2024/9/30
 *
 **/
@Aspect
@Component
@Order(Int.MIN_VALUE)
@Import(MyListener::class, MyEventResoure::class)
class ListenerAop {
    // 定义一个注解，注解的名字为RegisterListener，注解的类型为Annotation
    @Pointcut("@annotation(com.example.common.annotation.RegisterListener)")
    fun registerListener() {

    }

    @Around("registerListener()")
    fun around(joinPoint: Any,resoure: MyEventResoure) {
        println(joinPoint)
        println("============")
        println(resoure)
    }
}
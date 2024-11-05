package com.example.common

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.SecurityProperties.User
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.data.mongodb.core.MongoTemplate

/**
 *
 *@Auth yeyu
 *@Date 2024/9/29
 *
 **/
@EnableAspectJAutoProxy
@SpringBootApplication
class CommonMain

fun main(args: Array<String>) {
    val configurableApplicationContext = runApplication<CommonMain>(*args)
//    println(configurableApplicationContext.getBean("user"))
    // org.springframework.beans.factory.support.DefaultListableBeanFactory
//    println(configurableApplicationContext.getBeanProvider(User::class.java))

    var mongoTemplate = configurableApplicationContext.getBean(MongoTemplate::class.java)
    println(mongoTemplate.collectionNames)
    println(mongoTemplate.db.name)
}
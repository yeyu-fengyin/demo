package com.example.graphql.controller

import com.example.graphql.bean.User
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.web.bind.annotation.RestController

/**
 *@auth yeyu
 *@date 2024/11/5
 */
@RestController
class UserController {
    val users:List<User> = listOf(
        User(1,"zs",2),
        User(2,"lisi",3),
    )

    @QueryMapping
    fun getUser(@Argument id:Int): User {
        return users.first {
            it.id == id
        }
    }

    @QueryMapping
    fun allUsers(): List<User> {
        return users
    }
}
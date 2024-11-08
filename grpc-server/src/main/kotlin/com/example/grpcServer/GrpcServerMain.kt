package com.example.grpcServer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 *
 *@Auth yeyu
 *@Date 2024/11/8
 *
 */
@SpringBootApplication
class GrpcServerMain

fun main(args: Array<String>) {
    runApplication<GrpcServerMain>(*args)
}
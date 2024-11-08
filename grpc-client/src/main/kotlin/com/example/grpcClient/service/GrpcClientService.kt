package com.example.grpcClient.service

import com.example.grpc.proto.Department
import com.example.grpc.proto.DepartmentServiceGrpc
import com.example.grpc.proto.UserServiceGrpc
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

/**
 *
 *@Auth yeyu
 *@Date 2024/11/8
 *
 */
@Service
class GrpcClientService {
    @GrpcClient("local-grpc-server")
    lateinit var stb : UserServiceGrpc.UserServiceBlockingStub

    @GrpcClient("local-grpc-server")
    lateinit var dstb:DepartmentServiceGrpc.DepartmentServiceStub

    @PostConstruct
    fun getUser() {
        val user = stb.getUser(
            com.example.grpc.proto.Department.newBuilder()
                .setName("jw")
                .build()
        )
        println(user?.name)

        val value = object : StreamObserver<Department> {

            override fun onNext(p0: Department?) {
                println(p0)
            }

            override fun onError(t: Throwable?) {
                println(t)
            }

            override fun onCompleted() {
                println("client onCompleted")
            }
        }

        println("=============")
        val department = dstb.getDepartment(value)
        for (index in 1..10) {
            department.onNext(
                com.example.grpc.proto.User.newBuilder()
                    .setName("jw$index")
                    .build()
            )
        }
        department.onNext(
            com.example.grpc.proto.User.newBuilder()
                .setName("stop")
                .build()
        )
        department.onCompleted()

        Thread.sleep(10000)
    }
}
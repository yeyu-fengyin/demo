package com.example.grpcServer.service

import com.example.grpc.proto.Department
import com.example.grpc.proto.DepartmentServiceGrpc
import com.example.grpc.proto.User
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService

/**
 *
 *@Auth yeyu
 *@Date 2024/11/8
 *
 */
@GRpcService
class GrpcDepartmentService:DepartmentServiceGrpc.DepartmentServiceImplBase() {
     override fun getDepartment(responseObserver: StreamObserver<Department>?): StreamObserver<User> {
        val value = object : StreamObserver<User> {
            override fun onNext(value: User?) {
                println(value)
                if (value != null) {
                    responseObserver?.onNext(
                        Department.newBuilder()
                            .setName("${value.name} return")
                            .build()
                    )
                }
                if (value?.name == "stop") {
                    responseObserver?.onCompleted()
                }
            }

            override fun onError(t: Throwable?) {
                println(t)
            }

            override fun onCompleted() {
                println("server onCompleted")
//                responseObserver?.onCompleted()
            }

        }
         return value
    }
}
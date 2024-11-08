package com.example.grpcServer.service

import com.example.grpc.proto.Department
import com.example.grpc.proto.User
import com.example.grpc.proto.UserServiceGrpc
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService


/**
 *
 *@Auth yeyu
 *@Date 2024/11/8
 *
 */
@GRpcService
class GrpcUserService: UserServiceGrpc.UserServiceImplBase() {
    override fun getUser(request: Department?, responseObserver: StreamObserver<User>?) {
        User.newBuilder().setName(request?.name).build().let {
            responseObserver?.onNext(it)
            responseObserver?.onCompleted()
        }
    }
}
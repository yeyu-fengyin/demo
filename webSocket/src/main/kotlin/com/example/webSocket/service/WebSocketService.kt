package com.example.webSocket.service

import com.example.webSocket.config.ChainWebSocketListener
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket

/**
 *@auth yeyu
 *@date 2024/10/29
 */
class WebSocketService {
    fun connect(uri:String):WebSocket{
        val client = OkHttpClient.Builder()
            .build()
        return client.newWebSocket(Request.Builder().url(uri).header("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3MzAxOTQwMTAsImlhdCI6MTczMDE5MzcxMCwibmJmIjoxNzMwMTkzNzEwfQ.EQUCDFiMXMgYM_nP_oovH8dv_2BkCRKb2K3iVL5quvI").build(), ChainWebSocketListener())
    }
}

fun main(args: Array<String>) {
//    val service = WebSocketService()
//    val connect = service.connect("ws://192.168.2.7:51300")
//    connect.send("{\n" +
//            "    \"jsonrpc\": \"2.0\",\n" +
//            "    \"method\": \"latc_subscribe\",\n" +
//            "    \"params\": [\n" +
//            "        \"monitorData\"\n" +
//            "    ],\n" +
//            "    \"id\": 1\n" +
//            "}")

//    connect.send("{\n" +
//            "    \"jsonrpc\": \"2.0\",\n" +
//            "    \"method\": \"latc_subscribe\",\n" +
//            "    \"params\": [\n" +
//            "        \"newTBlock\"\n" +
//            "    ],\n" +
//            "    \"id\": 1\n" +
//            "}")
//
//    connect.send("{\n" +
//            "    \"jsonrpc\": \"2.0\",\n" +
//            "    \"method\": \"latc_subscribe\",\n" +
//            "    \"params\": [\n" +
//            "        \"newDBlock\"\n" +
//            "    ],\n" +
//            "    \"id\": 1\n" +
//            "}")

}
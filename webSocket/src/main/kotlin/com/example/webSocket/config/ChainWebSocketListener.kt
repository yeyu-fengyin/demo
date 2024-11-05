package com.example.webSocket.config

import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

/**
 *@auth yeyu
 *@date 2024/10/29
 */
class ChainWebSocketListener: WebSocketListener() {
    override fun onOpen(webSocket: WebSocket, response: Response) {
        println("onOpen")
    }
    override fun onMessage(webSocket: WebSocket, text: String) {
        println("onMessage: $text")
    }
    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        println("onClosing")
    }
    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        println("onClosed")
    }
    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        println(t)
        println("onFailure")
    }

}
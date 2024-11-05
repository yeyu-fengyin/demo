package com.example.webSocketServer.config

import jakarta.websocket.*
import jakarta.websocket.server.ServerEndpoint
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

/**
 *@auth yeyu
 *@date 2024/10/31
 */
@ServerEndpoint(value = "/ws")
@Component
class WebSocketEndpoint {

    private val log = LoggerFactory.getLogger(WebSocketEndpoint::class.java)
    val hashSet : ConcurrentHashMap<Session, Int> = ConcurrentHashMap()

    @OnOpen
    public fun onOpen(session: Session) {
        log.info("session opened: {}", session)
        hashSet.computeIfAbsent(session) { _ -> 0 }
    }

    @OnMessage
    public fun onMessage(session: Session, message: String) {
        log.info("message: {}", message)
        var absent = hashSet.computeIfAbsent(session) { _ -> 0 }
        absent+=message.toInt()
        send(session,absent.toString())
    }

    public fun send(session: Session, message: String) {
        session.asyncRemote.sendText(message)
    }

    @OnClose
    public fun onClose(session: Session, reason: CloseReason) {
        log.info("session closed: {} reason {}", session , reason.toString())
    }
}
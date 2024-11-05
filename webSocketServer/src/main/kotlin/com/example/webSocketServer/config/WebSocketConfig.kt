package com.example.webSocketServer.config

import jakarta.websocket.server.ServerContainer
import org.eclipse.jetty.ee10.servlet.ServletContextHandler
import org.eclipse.jetty.ee10.websocket.jakarta.server.JakartaWebSocketServerContainer
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.websocket.core.server.WebSocketServerComponents
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener
import org.springframework.web.socket.server.standard.ServerEndpointExporter


/**
 *@auth yeyu
 *@date 2024/10/31
 */
@Configuration
class WebSocketConfig {

    @Value("\${server.webSocketPort}")
    var webSocketPort:Int = 8091

//    @Bean
//    fun webSocketFactory():JettyServletWebServerFactory{
//        val factory = JettyServletWebServerFactory()
//        factory.port = webSocketPort
//        return factory
//    }

    @EventListener(ApplicationReadyEvent::class)
    @Throws(Exception::class)
    fun startWebSocketServer() {
        // 创建一个独立的 Jetty 服务器实例
        val server: Server = Server(webSocketPort)
        val context = ServletContextHandler(ServletContextHandler.SESSIONS)
        context.contextPath = "/"

        // 设置 WebSocket 端点
        server.setHandler(context)


        WebSocketServerComponents.getWebSocketComponents(server).start()

        // 启动 WebSocket 独立服务器
//        server.start()
    }

    @Bean
    fun serverEndpointExporter(): ServerEndpointExporter {
        return ServerEndpointExporter()
    }
}
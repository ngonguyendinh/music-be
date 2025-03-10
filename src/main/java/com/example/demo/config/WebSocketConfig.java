package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // Đăng ký endpoint mà client sẽ kết nối (sử dụng SockJS cho trình duyệt không hỗ trợ WebSocket)
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOrigins("*") // Chỉ dùng "*" cho môi trường dev, production cần cấu hình đúng origins
                .withSockJS();
    }

    // Cấu hình Message Broker (ở đây sử dụng broker đơn giản)
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Các message gửi từ server tới client sẽ có đuôi /topic hoặc /queue
        registry.enableSimpleBroker("/topic", "/queue");
        // Định nghĩa prefix cho các message gửi từ client tới server
        registry.setApplicationDestinationPrefixes("/app");
    }
}

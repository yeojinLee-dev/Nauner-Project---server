package com.example.nanuer_server.config.Chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import com.example.nanuer_server.config.Chat.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    //@Autowired
    //SocketHandler socketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(signalingSocektHandelr(), "/room")
                .setAllowedOrigins("*");
    }
    @Bean
    public WebSocketHandler signalingSocektHandelr(){
        return new WebSocketHandler();
    }
}

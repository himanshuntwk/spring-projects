package com.himanshu.websocketclientserver.clients;

import com.himanshu.websocketclientserver.server.IncomingMessage;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.util.concurrent.ExecutionException;

/**
 * This client send its message directly to common topic for communication.
 */
public class ClientTwoDirect {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        WebSocketClient client = new StandardWebSocketClient();

        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        ClientTwoDirectSessionHandler clientTwoDirectSessionHandler = new ClientTwoDirectSessionHandler();
        ListenableFuture<StompSession> sessionAsync =
                stompClient.connect("ws://localhost:8080/websocket-server", clientTwoDirectSessionHandler);
        StompSession session = sessionAsync.get();
        session.subscribe("/topic/messages", clientTwoDirectSessionHandler);
        while (true) {
            session.send("/topic/messages", new IncomingMessage("Direct :: Henry " + System.currentTimeMillis()));
            Thread.sleep(2000);
        }
    }
}

class ClientTwoDirectSessionHandler extends StompSessionHandlerAdapter {

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return IncomingMessage.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        System.out.println("Received : " + ((IncomingMessage) payload).getName());
    }
}
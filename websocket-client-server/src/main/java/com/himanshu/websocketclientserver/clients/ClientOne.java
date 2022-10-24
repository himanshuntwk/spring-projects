package com.himanshu.websocketclientserver.clients;

import com.himanshu.websocketclientserver.server.IncomingMessage;
import com.himanshu.websocketclientserver.server.OutgoingMessage;
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

public class ClientOne {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        WebSocketClient client = new StandardWebSocketClient();

        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        ClientOneSessionHandler clientOneSessionHandler = new ClientOneSessionHandler();
        ListenableFuture<StompSession> sessionAsync =
                stompClient.connect("ws://localhost:8080/websocket-server", clientOneSessionHandler);
        StompSession session = sessionAsync.get();
        session.subscribe("/topic/messages", clientOneSessionHandler);
        while (true) {
            session.send("/app/process-message", new IncomingMessage("Himanshu " + System.currentTimeMillis()));
            Thread.sleep(2000);
        }
    }
}

class ClientOneSessionHandler extends StompSessionHandlerAdapter {

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return IncomingMessage.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        System.out.println("Received : " + ((IncomingMessage) payload).getName());
    }
}
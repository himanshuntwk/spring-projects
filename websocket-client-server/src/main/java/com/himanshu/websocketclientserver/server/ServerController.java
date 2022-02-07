package com.himanshu.websocketclientserver.server;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ServerController {
 
  @MessageMapping("/process-message")
    @SendTo("/topic/messages")
    public OutgoingMessage processMessage(IncomingMessage incomingMessage) throws Exception{
        Thread.sleep(1000);
        return new OutgoingMessage("Hello " + incomingMessage.getName());
    }
}

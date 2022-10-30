package com.practice.kafkademo.listener;

import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;

//@Component
public class KafkaDemoListener {

//  @KafkaListener(id = "listenDemoId", topics = "demo-topic3", groupId = "test-group")
  public void listenTopic(String inputMessage) {
    System.out.println("Listener 1 >>> " + inputMessage);
  }
}

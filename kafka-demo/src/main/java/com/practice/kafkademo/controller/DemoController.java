package com.practice.kafkademo.controller;

import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controller class to initiate send message to kafka topic.
 */
//@RestController
//@RequestMapping("/kafka-demo")
public class DemoController {

//  private KafkaTemplate<String, String> kafkaTemplate;

//  public DemoController(KafkaTemplate<String, String> kafkaTemplate) {
//    this.kafkaTemplate = kafkaTemplate;
//  }

  //  @PostMapping
  public void sendMessage(@RequestBody String message) {
//    kafkaTemplate.send("demo-topic3", message);
//    ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send("demo-topic3", message);
//    send.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
//      @Override
//      public void onFailure(Throwable ex) {
//        System.out.println("error occurred while sending...");
//      }
//
//      @Override
//      public void onSuccess(SendResult<String, String> result) {
//        System.out.println("Successfully sent to the topic...");
//      }
//    });
  }

}

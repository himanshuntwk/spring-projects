package com.himanshu.grpcserver;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import helloworld.GreetingServiceGrpc;
import helloworld.ResponseMessage;
import helloworld.RequestMessage;

import java.util.concurrent.TimeUnit;

public class GreetingClient {

  public static void main(String[] args) throws Exception {
    final ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565)
            .usePlaintext()
            .build();

    RequestMessage request = RequestMessage.newBuilder().setName("Himanshu").build();
    ResponseMessage response;
    try {
      response = GreetingServiceGrpc.newBlockingStub(channel).sayHello(request);
      System.out.println(response.getMessage());
    } catch (StatusRuntimeException e) {
      return;
    } finally {
      channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
    }
  }
}

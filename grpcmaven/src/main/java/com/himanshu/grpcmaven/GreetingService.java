package com.himanshu.grpcmaven;

import helloworld.GreetingServiceGrpc;
import helloworld.RequestMessage;
import helloworld.ResponseMessage;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class GreetingService extends GreetingServiceGrpc.GreetingServiceImplBase{
  /**
   * Server side method to greet user on request.
   * @param req
   * @param responseObserver
   */
  @Override
  public void sayHello(RequestMessage req, StreamObserver<ResponseMessage> responseObserver) {
    ResponseMessage reply = ResponseMessage.newBuilder()
            .setMessage("Hello " + req.getName() + ", from server").build();
    responseObserver.onNext(reply);
    responseObserver.onCompleted();
  }
}

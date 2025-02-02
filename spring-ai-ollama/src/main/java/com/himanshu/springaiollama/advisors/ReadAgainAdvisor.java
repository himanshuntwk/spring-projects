package com.himanshu.springaiollama.advisors;

import org.springframework.ai.chat.client.advisor.api.AdvisedRequest;
import org.springframework.ai.chat.client.advisor.api.AdvisedResponse;
import org.springframework.ai.chat.client.advisor.api.CallAroundAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAroundAdvisorChain;
import org.springframework.stereotype.Component;

@Component
public class ReadAgainAdvisor implements CallAroundAdvisor {


  @Override
  public AdvisedResponse aroundCall(AdvisedRequest advisedRequest, CallAroundAdvisorChain chain) {
    System.out.println("ReadAgainAdvisor started....");
    String userInput = advisedRequest.userText();
    AdvisedRequest updatedRequest = AdvisedRequest.from(advisedRequest).userText("Read the question again : " + userInput).build();
    AdvisedResponse advisedResponse = chain.nextAroundCall(updatedRequest);
    return advisedResponse;
  }

  @Override
  public String getName() {
    return "ReadAgainAdvisor";
  }

  @Override
  public int getOrder() {
    return 0;
  }
}

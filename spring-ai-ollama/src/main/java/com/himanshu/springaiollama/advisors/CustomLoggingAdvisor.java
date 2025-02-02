package com.himanshu.springaiollama.advisors;

import org.springframework.ai.chat.client.advisor.api.AdvisedRequest;
import org.springframework.ai.chat.client.advisor.api.AdvisedResponse;
import org.springframework.ai.chat.client.advisor.api.CallAroundAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAroundAdvisorChain;
import org.springframework.stereotype.Component;

@Component
public class CustomLoggingAdvisor implements CallAroundAdvisor {

  @Override
  public AdvisedResponse aroundCall(AdvisedRequest advisedRequest, CallAroundAdvisorChain chain) {
    System.out.println("CustomLoggingAdvisor aroundCall start :: " + advisedRequest.userText());
    AdvisedResponse advisedResponse = chain.nextAroundCall(advisedRequest);
    return advisedResponse;
  }

  @Override
  public String getName() {
    return "CustomLoggingAdvisor";
  }

  @Override
  public int getOrder() {
    return 1;
  }
}

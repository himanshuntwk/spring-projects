package com.himanshu.graphqlserver;

import com.netflix.graphql.dgs.client.GraphQLResponse;
import com.netflix.graphql.dgs.client.MonoGraphQLClient;
import com.netflix.graphql.dgs.client.WebClientGraphQLClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Graph QL implementation using DGS framework.
 */
@SpringBootApplication
public class GraphqlServerApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(GraphqlServerApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    WebClient webClient = WebClient.create("http://localhost:8080/graphql");
    WebClientGraphQLClient client = MonoGraphQLClient.createWithWebClient(webClient);

    Mono<GraphQLResponse> graphQLResponseMono = client.reactiveExecuteQuery("{ searchPerson(searchPersonId: 1) { personId personName}}");

    Mono<String> somefield = graphQLResponseMono.map(r -> r.extractValue("data.searchPerson.personName"));
    somefield.subscribe(System.out::print);
  }
}

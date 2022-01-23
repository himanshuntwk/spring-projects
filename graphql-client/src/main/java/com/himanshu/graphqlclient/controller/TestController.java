package com.himanshu.graphqlclient.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.graphql.dgs.client.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    @GetMapping("/test-client")
    public void testClient() throws InterruptedException {
        RequestExecutor requestExecutor = (url, headers, body) -> {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders httpHeaders = new HttpHeaders();
            headers.forEach(httpHeaders::addAll);
            ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(body, httpHeaders), String.class);
            return new HttpResponse(exchange.getStatusCodeValue(), exchange.getBody(), exchange.getHeaders());
        };
        GraphQLClient client = GraphQLClient.createCustom("http://localhost:8080/graphql", requestExecutor);
        GraphQLResponse response =
                client.executeQuery("{ searchPerson(searchPersonId: 1) { personId personName personAddress {streetName houseNumber city country }}}");

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response.getJson());
            System.out.println(node.get("data").get("searchPerson").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

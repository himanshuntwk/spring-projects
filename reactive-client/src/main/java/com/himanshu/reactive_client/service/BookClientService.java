package com.himanshu.reactive_client.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.himanshu.reactive_client.model.BookDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.print.Book;
import java.util.Map;

@Service
public class BookClientService {

  private final WebClient webClient;

  public BookClientService(WebClient webClient) {
    this.webClient = webClient;
  }

  public Flux<String> listBooksString() {
    return webClient.get().uri("/book").retrieve().bodyToFlux(String.class);
  }

  public Flux<Map> listBooksMap() {
    return webClient.get().uri("/book").retrieve().bodyToFlux(Map.class);
  }

  public Flux<JsonNode> listBooksJsonNode() {
    return webClient.get().uri("/book").retrieve().bodyToFlux(JsonNode.class);
  }

  public Flux<BookDTO> listBooksDTO() {
    return webClient.get().uri("/book").retrieve().bodyToFlux(BookDTO.class);
  }

  public Mono<BookDTO> getBookById(Integer bookId) {
    return webClient.get().uri("/book/{bookId}", bookId).retrieve().bodyToMono(BookDTO.class);
  }

  public Mono<BookDTO> saveBook(BookDTO bookDTO) {
    return webClient.post().uri("/book")
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(bookDTO), BookDTO.class)
        .retrieve().bodyToMono(BookDTO.class);
  }

  public Mono<BookDTO> updateBook(BookDTO bookDTO) {
    return webClient.put().uri("/book")
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(bookDTO), BookDTO.class)
        .retrieve().bodyToMono(BookDTO.class);
  }

  public Mono<Void> deleteBookById(Integer bookId) {
    return webClient.delete().uri("/book/{bookId}", bookId).retrieve().bodyToMono(Void.class);
  }

}

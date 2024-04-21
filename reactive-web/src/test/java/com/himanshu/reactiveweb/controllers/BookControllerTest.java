package com.himanshu.reactiveweb.controllers;

import com.himanshu.reactiveweb.model.BookDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureWebTestClient
class BookControllerTest {

  @Autowired
  WebTestClient client;

  @Test
  @Order(1)
  void testSaveBook() {
    client.post().uri("/book")
        .body(Mono.just(getBook()), BookDTO.class)
        .header("Content-Type", "application/json")
        .exchange()
        .expectStatus().isOk();
  }

  @Test
  @Order(2)
  void testUpdateBook() {
    BookDTO updatedBook = getBook();
    updatedBook.setId(1);
    updatedBook.setTitle("Updated");
    client.put().uri("/book")
        .body(Mono.just(updatedBook), BookDTO.class)
        .header("Content-Type", "application/json")
        .exchange()
        .expectStatus().isOk();
  }

  @Test
  @Order(3)
  void testListBooks() {
    client.get().uri("/book").exchange().expectStatus().isOk()
        .expectBody().jsonPath("$.size()").isEqualTo(1);
  }

  @Test
  @Order(4)
  void testGetBookById() {
    client.get().uri("/book/1").exchange().expectStatus().isOk()
        .expectBody().jsonPath("$.title").isEqualTo("Updated");
  }

  @Test
  @Order(5)
  void testDeleteBookById() {
    client.delete().uri("/book/1").exchange().expectStatus().isNoContent();
    client.get().uri("/book/1").exchange().expectStatus().isOk()
        .expectBody().isEmpty();
  }



  private static BookDTO getBook() {
    return BookDTO.builder()
        .title("Introduction to something")
        .isbn("I12-345ds")
        .author("Himanshu")
        .genre("Thriller")
        .publisher("Own co.")
        .build();
  }

}
package com.himanshu.reactive_client.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.himanshu.reactive_client.model.BookDTO;
import com.himanshu.reactive_client.service.BookClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RequestMapping("/bookclient")
@RestController
public class BookClientController {

  private final BookClientService bookClientService;

  public BookClientController(BookClientService bookClientService) {
    this.bookClientService = bookClientService;
  }

  @GetMapping("/string")
  public Flux<String> listBooksString() {
    return bookClientService.listBooksString();
  }

  @GetMapping("/map")
  public Flux<Map> listBooksMap() {
    return bookClientService.listBooksMap().map(recmap -> Map.of(recmap.get("id"),recmap.get("title")));
  }

  @GetMapping("/node")
  public Flux<JsonNode> listBooksJsonNode() {
    return bookClientService.listBooksJsonNode().map(node -> node.get("title"));
  }

  @GetMapping("/dto")
  public Flux<BookDTO> listBooksDTO() {
    return bookClientService.listBooksDTO().map(dto -> {
      dto.setTitle(dto.getTitle() + " updated");
      return dto;
    });
  }

  @GetMapping("/{bookId}")
  public Mono<BookDTO> getBookById(@PathVariable Integer bookId) {
    return bookClientService.getBookById(bookId);
  }

  @PostMapping
  public Mono<BookDTO> saveBook(@RequestBody BookDTO bookDTO) {
    return bookClientService.saveBook(bookDTO);
  }

  @PutMapping
  public Mono<BookDTO> updateBook(@RequestBody BookDTO bookDTO) {
    return bookClientService.updateBook(bookDTO);
  }

  @DeleteMapping("/{bookId}")
  public Mono<ResponseEntity<Void>> deleteBookById(@PathVariable Integer bookId) {
    return bookClientService.deleteBookById(bookId).thenReturn(ResponseEntity.noContent().build());
  }


}

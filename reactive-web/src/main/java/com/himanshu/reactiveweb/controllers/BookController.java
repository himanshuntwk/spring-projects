package com.himanshu.reactiveweb.controllers;

import com.himanshu.reactiveweb.model.BookDTO;
import com.himanshu.reactiveweb.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/book")
@RestController
@RequiredArgsConstructor
public class BookController {

  private final BookService bookService;

  @GetMapping
  Flux<BookDTO> listBooks() {
    return bookService.listBooks();
  }

  @PostMapping
  Mono<BookDTO> saveBook(@RequestBody BookDTO bookDTO) {
    return bookService.saveBook(bookDTO);
  }

  @PutMapping
  Mono<BookDTO> updateBook(@RequestBody BookDTO bookDTO) {
    return bookService.updateBook(bookDTO);
  }

  @GetMapping("/{bookId}")
  Mono<BookDTO> getBookByid(@PathVariable Integer bookId) {
    return bookService.findBookById(bookId);
  }

  @DeleteMapping("/{bookId}")
  Mono<ResponseEntity<Void>> deleteBookByid(@PathVariable Integer bookId) {
    return bookService.deleteBookById(bookId).thenReturn(ResponseEntity.noContent().build());
  }

}

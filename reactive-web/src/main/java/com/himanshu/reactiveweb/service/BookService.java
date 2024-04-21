package com.himanshu.reactiveweb.service;

import com.himanshu.reactiveweb.domain.Book;
import com.himanshu.reactiveweb.model.BookDTO;
import com.himanshu.reactiveweb.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookRepository bookRepository;

  public Flux<BookDTO> listBooks() {
    return bookRepository.findAll()
        .map(book -> BookDTO.builder()
            .id(book.getId())
            .title(book.getTitle())
            .author(book.getAuthor())
            .publisher(book.getPublisher())
            .isbn(book.getIsbn())
            .genre(book.getGenre())
            .build());
  }

  public Mono<BookDTO> saveBook(BookDTO bookDTO) {
    Book book = Book.builder()
        .title(bookDTO.getTitle())
        .author(bookDTO.getAuthor())
        .publisher(bookDTO.getPublisher())
        .isbn(bookDTO.getIsbn())
        .genre(bookDTO.getGenre())
        .build();

    return bookRepository.save(book).map(savedBook -> BookDTO.builder()
        .id(savedBook.getId())
        .title(savedBook.getTitle())
        .author(savedBook.getAuthor())
        .publisher(savedBook.getPublisher())
        .isbn(savedBook.getIsbn())
        .genre(savedBook.getGenre())
        .build());
  }


  public Mono<BookDTO> updateBook(BookDTO bookDTO) {

    return bookRepository.findById(bookDTO.getId()).map(savedBook -> {
      savedBook.setTitle(bookDTO.getTitle());
      savedBook.setAuthor(bookDTO.getAuthor());
      savedBook.setPublisher(bookDTO.getPublisher());
      savedBook.setIsbn(bookDTO.getIsbn());
      savedBook.setGenre(bookDTO.getGenre());
      return savedBook;
    }).flatMap(bookRepository::save)
        .map(savedBook -> BookDTO.builder()
        .id(savedBook.getId())
        .title(savedBook.getTitle())
        .author(savedBook.getAuthor())
        .publisher(savedBook.getPublisher())
        .isbn(savedBook.getIsbn())
        .genre(savedBook.getGenre())
        .build());
  }

  public Mono<BookDTO> findBookById(Integer bookId) {
    return bookRepository.findById(bookId)
        .map(book -> BookDTO.builder()
            .id(book.getId())
            .title(book.getTitle())
            .author(book.getAuthor())
            .publisher(book.getPublisher())
            .isbn(book.getIsbn())
            .genre(book.getGenre())
            .build());
  }

  public Mono<Void> deleteBookById(Integer bookId) {
    return bookRepository.deleteById(bookId);
  }

}

package com.himanshu.reactiveweb.repositories;

import com.himanshu.reactiveweb.config.DatabaseConfig;
import com.himanshu.reactiveweb.domain.Book;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import reactor.test.StepVerifier;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataR2dbcTest
@Import(DatabaseConfig.class)
class BookRepositoryTest {

  @Autowired
  BookRepository bookRepository;

  @Test
  @Order(1)
  void testSaveNewBook() {
    bookRepository.save(getBook()).subscribe(book -> System.out.println(book.toString()));
  }

  @Test
  @Order(2)
  void testFindBook() {
    StepVerifier.create(bookRepository.findAll()).expectNextCount(1).verifyComplete();
  }

  @Test
  @Order(3)
  void testFindBookById() {
    StepVerifier.create(bookRepository.findById(1)).expectNextCount(1).verifyComplete();
    StepVerifier.create(bookRepository.findById(2)).expectNextCount(0).verifyComplete();
  }


  private static Book getBook() {
    return Book.builder()
        .title("Introduction to something")
        .isbn("I12-345ds")
        .author("Himanshu")
        .genre("Thriller")
        .publisher("Own co.")
        .build();
  }


}
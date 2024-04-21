package com.himanshu.reactiveweb.repositories;

import com.himanshu.reactiveweb.domain.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BookRepository extends ReactiveCrudRepository<Book, Integer> {
}

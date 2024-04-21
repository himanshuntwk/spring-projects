package com.himanshu.reactiveweb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

  private Integer id;
  private String title;
  private String author;
  private String publisher;
  private String isbn;
  private String genre;
}

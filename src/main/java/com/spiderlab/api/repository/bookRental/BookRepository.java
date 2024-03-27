package com.spiderlab.api.repository.bookRental;


import com.spiderlab.api.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {

}

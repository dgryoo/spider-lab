package com.spiderlab.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "isbn")
    private String isbn;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "rental_count", nullable = false)
    private int rentalCount;

    public static Book create(String isbn, String name) {
        Book book = new Book();
        book.isbn = isbn;
        book.name = name;
        return book;
    }

    public void increaseRentalCount() {
        rentalCount += 1;
    }

}

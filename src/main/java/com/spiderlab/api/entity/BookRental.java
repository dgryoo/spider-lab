package com.spiderlab.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "book_rental")
public class BookRental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consignor_id")
    private Member consignor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "price")
    private int price;

    @Column(name = "last_rental_datetime")
    private LocalDateTime lastRentalDatetime = null;

    @Column(name = "rented")
    private boolean rented = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "renter_id")
    private Member renter = null;

    @CreationTimestamp
    @Column(name = "registered_at")
    private LocalDateTime registeredAt;

    public static BookRental create(Member consignor, Book book, int price) {
        BookRental bookRental = new BookRental();
        bookRental.consignor = consignor;
        bookRental.book = book;
        bookRental.price = price;
        return bookRental;
    }

    public void rent(Member renter) {
        this.rented = true;
        this.renter = renter;
        this.lastRentalDatetime = LocalDateTime.now();
    }

}

package com.spiderlab.api.dto.bookRental;

import com.spiderlab.api.entity.BookRental;
import lombok.Getter;

@Getter
public class BookRentalDto {

    private long id;

    private String bookName;

    private int price;

    private String consignorName;

    public static BookRentalDto fromEntity(BookRental bookRental) {
        BookRentalDto bookRentalDto = new BookRentalDto();
        bookRentalDto.id = bookRental.getId();
        bookRentalDto.bookName = bookRental.getBook().getName();
        bookRentalDto.price = bookRental.getPrice();
        bookRentalDto.consignorName = bookRental.getConsignor().getName();
        return bookRentalDto;

    }

}

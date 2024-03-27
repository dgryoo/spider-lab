package com.spiderlab.api.dto.bookRental;

import lombok.Getter;

@Getter
public class RegisterBookRentalRequest {
    String bookName;

    String isbn;

    int price;
}

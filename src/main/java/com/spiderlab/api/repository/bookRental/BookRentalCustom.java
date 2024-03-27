package com.spiderlab.api.repository.bookRental;

import com.spiderlab.api.entity.BookRental;

import java.util.List;

public interface BookRentalCustom {
    List<BookRental> findAllByFilter(int page, String filterBy);

    void returnBookRental(List<Long> ids);

}

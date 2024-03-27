package com.spiderlab.api.repository.bookRental;


import com.spiderlab.api.entity.BookRental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRentalRepository extends JpaRepository<BookRental, Long>, BookRentalCustom {
    List<BookRental> findAllByIdIn(List<Long> ids);

    List<BookRental> findByRentedIsTrue();

}

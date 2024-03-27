package com.spiderlab.api.controller;

import com.spiderlab.api.dto.bookRental.RegisterBookRentalRequest;
import com.spiderlab.api.dto.bookRental.RentBookRequest;
import com.spiderlab.api.dto.bookRental.BookRentalDto;
import com.spiderlab.api.serivce.BookRentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/book-rental")
@RequiredArgsConstructor
public class BookRentalController {

    private final BookRentalService bookRentalService;

    @GetMapping("")
    public List<BookRentalDto> getBookRentalList(@RequestParam(value = "page", defaultValue = "1") int page,
                                                 @RequestParam(value = "filterBy", defaultValue = "RENTAL_COUNT") String filterBy) {
        return bookRentalService.getBookRentalList(page, filterBy);
    }

    @PostMapping("/rent")
    public void rentBooks(@RequestHeader("X-User-Id") String userId, @RequestBody RentBookRequest rentBookRequest) {
        bookRentalService.rentBooks(userId, rentBookRequest);
    }

    @PostMapping("/register")
    public void registerBookRental(@RequestHeader("X-User-Id") String userId, @RequestBody RegisterBookRentalRequest registerBookRentalRequest) {
        bookRentalService.registerBookRental(userId, registerBookRentalRequest);
    }
}

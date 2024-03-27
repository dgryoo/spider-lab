package com.spiderlab.api.dto.bookRental;

import lombok.Getter;

import java.util.List;

@Getter
public class RentBookRequest {
    private List<Long> bookIds;

}

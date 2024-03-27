package com.spiderlab.api.schedule;

import com.spiderlab.api.serivce.BookRentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    private final BookRentalService bookRentalService;

    @Scheduled(cron = "* * * * * *")
    public void returnBooks() {
        bookRentalService.returnBooks();
    }
}

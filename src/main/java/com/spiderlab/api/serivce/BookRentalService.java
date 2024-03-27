package com.spiderlab.api.serivce;


import com.spiderlab.api.common.exception.custom.BadRequestException;
import com.spiderlab.api.common.exception.custom.SourceNotFoundException;
import com.spiderlab.api.dto.bookRental.BookRentalDto;
import com.spiderlab.api.dto.bookRental.RegisterBookRentalRequest;
import com.spiderlab.api.dto.bookRental.RentBookRequest;
import com.spiderlab.api.entity.Book;
import com.spiderlab.api.entity.BookRental;
import com.spiderlab.api.entity.Member;
import com.spiderlab.api.repository.bookRental.BookRentalRepository;
import com.spiderlab.api.repository.bookRental.BookRepository;
import com.spiderlab.api.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class BookRentalService {
    private final MemberRepository memberRepository;

    private final BookRentalRepository bookRentalRepository;

    private final BookRepository bookRepository;

    @Transactional(readOnly = true)
    public List<BookRentalDto> getBookRentalList(int page, String filterBy) {
        // 필터를 적용하여 책 대여 정보 조회
        List<BookRental> bookRentalList = bookRentalRepository.findAllByFilter(page, filterBy);

        // 책 정보 리턴
        return bookRentalList.stream()
                .map(BookRentalDto::fromEntity)
                .toList();

    }

    public void rentBooks(String userId, RentBookRequest rentBookRequest) {
        // 멤버 조회, 없을 경우 예외 발생
        Member member = memberRepository.findById(userId).orElseThrow(SourceNotFoundException::new);

        // 책 대여 정보 조회
        List<BookRental> bookRentalList = bookRentalRepository.findAllByIdIn(rentBookRequest.getBookIds());

        // 요청된 대여정보 확인
        if (rentBookRequest.getBookIds().size() != bookRentalList.size()) {
            throw new SourceNotFoundException();
        }

        // 책 대여
        bookRentalList.forEach(bookRental -> {
            // 책이 이미 대여중일 경우
            if (bookRental.isRented()) {
                throw new BadRequestException("올바르지 않은 형식의 요청입니다.", "이미 대여된 책입니다.");
            }
            // 책 대여, 책 대여 수 증가
            bookRental.rent(member);
            bookRental.getBook().increaseRentalCount();
        });


    }

    public void registerBookRental(String userId, RegisterBookRentalRequest registerBookRentalRequest) {
        // 멤버 조회, 없을 경우 예외 발생
        Member member = memberRepository.findById(userId).orElseThrow(SourceNotFoundException::new);

        // 책 조회
        Book book = bookRepository.findById(registerBookRentalRequest.getIsbn()).orElse(null);

        // 책이 저장되어 있지 않을 때 생성 후 저장
        if (book == null) {
            book = Book.create(registerBookRentalRequest.getIsbn(), registerBookRentalRequest.getBookName());
            bookRepository.save(book);
        }

        // 대여정보 생성
        BookRental bookRental = BookRental.create(member, book, registerBookRentalRequest.getPrice());

        // 대여정보 저장
        bookRentalRepository.save(bookRental);
    }

    public void returnBooks() {
        List<BookRental> rentedBookRentalList = bookRentalRepository.findByRentedIsTrue();

        // 렌탈중인 도서 중 10초이상 대여된 도서 분리
        LocalDateTime currentTime = LocalDateTime.now();
        List<Long> overdueBookRentalIds = rentedBookRentalList.stream()
                .filter(rental -> Duration.between(rental.getLastRentalDatetime(), currentTime).getSeconds() >= 10)
                .map(BookRental::getId)
                .toList();

        // 대여 종료 처리
        bookRentalRepository.returnBookRental(overdueBookRentalIds);
    }
}


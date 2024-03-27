package com.spiderlab.api.repository.bookRental;


import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.spiderlab.api.config.Querydsl5RepositorySupport;
import com.spiderlab.api.entity.BookRental;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.spiderlab.api.entity.QBook.book;
import static com.spiderlab.api.entity.QBookRental.bookRental;
import static com.spiderlab.api.entity.QMember.member;


@Repository
public class BookRentalRepositoryImpl extends Querydsl5RepositorySupport implements BookRentalCustom {
    public BookRentalRepositoryImpl() {
        super(BookRental.class);
    }

    @Override
    public List<BookRental> findAllByFilter(int page, String filterBy) {
        return selectFrom(bookRental)
                .where(bookRental.rented.eq(false))
                .leftJoin(bookRental.consignor, member)
                .leftJoin(bookRental.book, book)
                .orderBy(applyFilter(filterBy))
                .offset((page - 1) * 20)
                .limit(20)
                .fetch();
    }

    @Override
    public void returnBookRental(List<Long> ids) {
        getQueryFactory().update(bookRental)
                .set(bookRental.rented, false)
                .set(bookRental.renter, Expressions.nullExpression())
                .where(bookRental.id.in(ids))
                .execute();
    }


    private OrderSpecifier<?> applyFilter(String filterBy) {
        OrderSpecifier<?> orderSpecifier;
        if (BookRentalFilter.RENTAL_COUNT.name().equals(filterBy)) {
            orderSpecifier = bookRental.book.rentalCount.desc();
        } else if (BookRentalFilter.PRICE.name().equals(filterBy)) {
            orderSpecifier = bookRental.price.asc();
        } else if (BookRentalFilter.RECENT_REGISTERED.name().equals(filterBy)) {
            orderSpecifier = bookRental.id.desc();
        } else {
            orderSpecifier = bookRental.book.rentalCount.desc();
        }
        return orderSpecifier;
    }

}

package com.spiderlab.api.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookRental is a Querydsl query type for BookRental
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookRental extends EntityPathBase<BookRental> {

    private static final long serialVersionUID = -1527888815L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookRental bookRental = new QBookRental("bookRental");

    public final QBook book;

    public final QMember consignor;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> lastRentalDatetime = createDateTime("lastRentalDatetime", java.time.LocalDateTime.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> registeredAt = createDateTime("registeredAt", java.time.LocalDateTime.class);

    public final BooleanPath rented = createBoolean("rented");

    public final QMember renter;

    public QBookRental(String variable) {
        this(BookRental.class, forVariable(variable), INITS);
    }

    public QBookRental(Path<? extends BookRental> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBookRental(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBookRental(PathMetadata metadata, PathInits inits) {
        this(BookRental.class, metadata, inits);
    }

    public QBookRental(Class<? extends BookRental> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.book = inits.isInitialized("book") ? new QBook(forProperty("book")) : null;
        this.consignor = inits.isInitialized("consignor") ? new QMember(forProperty("consignor")) : null;
        this.renter = inits.isInitialized("renter") ? new QMember(forProperty("renter")) : null;
    }

}


package com.neetry.platform.book.adapter.in.presentation.controller.book.model;

import com.neetry.platform.book.domain.entity.basic.ModelStatus;
import com.neetry.platform.book.domain.model.book.BookModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record BookResponse(
        long id,
        String title,
        String author,
        String genre,
        String description,
        String isbn,
        String image,
        String publish,
        String publisher,
        LocalDateTime createdOn,
        LocalDateTime updatedOn,
        LocalDateTime deletedOn,
        ModelStatus status
) {
    public static BookResponse from(
            final BookModel book) {
        return new BookResponse(
                book.id(),
                book.title(),
                book.author(),
                book.genre(),
                book.description(),
                book.isbn(),
                book.image(),
                book.publish(),
                book.publisher(),
                book.createdOn(), 
                book.updatedOn(), 
                book.deletedOn(), 
                book.status()
                
        );
    }

    public static List<BookResponse> from(
            final List<BookModel> bookModels) {
        return bookModels.stream().map(BookResponse::from).collect(Collectors.toList());
    }
}

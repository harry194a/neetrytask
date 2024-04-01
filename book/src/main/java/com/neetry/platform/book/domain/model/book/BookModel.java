package com.neetry.platform.book.domain.model.book;

import com.neetry.platform.book.domain.entity.basic.ModelStatus;
import com.neetry.platform.book.domain.entity.book.Book;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Harutyun Badeyan
 * Date: 27.03.24
 * Time: 18:05
 */
public record BookModel(Long id,
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
    public static BookModel from(Book book) {
        return new BookModel(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getGenre(),
                book.getDescription(),
                book.getIsbn(),
                book.getImage(),
                book.getPublish(),
                book.getPublisher(),
                book.getAudit().getCreatedOn(),
                book.getAudit().getUpdatedOn(),
                book.getAudit().getDeletedOn(),
                book.getAudit().getStatus()
        );
    }

    public static List<BookModel> from(
            final List<Book> monitors) {
        return monitors.stream().map(BookModel::from).collect(Collectors.toList());
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public String author() {
        return author;
    }

    @Override
    public String genre() {
        return genre;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public String isbn() {
        return isbn;
    }

    @Override
    public String image() {
        return image;
    }

    @Override
    public String publish() {
        return publish;
    }

    @Override
    public String publisher() {
        return publisher;
    }

    @Override
    public LocalDateTime createdOn() {
        return createdOn;
    }

    @Override
    public LocalDateTime updatedOn() {
        return updatedOn;
    }

    @Override
    public LocalDateTime deletedOn() {
        return deletedOn;
    }

    @Override
    public ModelStatus status() {
        return status;
    }
}

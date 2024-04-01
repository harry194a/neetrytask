package com.neetry.platform.book.domain.model.book;

import com.neetry.platform.book.domain.entity.book.Book;

/**
 * Created by Harutyun Badeyan
 * Date: 27.03.24
 * Time: 18:05
 */
public record CreateBookModel(
        String title,
        String author,
        String genre,
        String description,
        String isbn,
        String image,
        String publish,
        String publisher
) {
    public Book toEntity() {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setDescription(description);
        book.setIsbn(isbn);
        book.setImage(image);
        book.setPublish(publish);
        book.setPublisher(publisher);
        return book;
    }
}

package com.neetry.platform.book.domain.model.book;

import com.neetry.platform.book.domain.entity.book.Book;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by Harutyun Badeyan
 * Date: 27.03.24
 * Time: 18:05
 */
public record UpdateBookModel(
        String title,
        String author,
        String genre,
        String description,
        String isbn,
        String image,
        String publish,
        String publisher) {
    public Book toEntity(Book book) {
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setDescription(description);
        book.setIsbn(isbn);
        book.setImage(image);
        book.setPublisher(publish);
        book.setPublish(publisher);
        return book;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof final UpdateBookModel that)) return false;

        return new EqualsBuilder()
                .append(title, that.title)
                .append(author, that.author)
                .append(genre, that.genre)
                .append(description, that.description)
                .append(isbn, that.isbn)
                .append(image, that.image)
                .append(publish, that.publish)
                .append(publisher, that.publisher).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(title)
                .append(author)
                .append(genre)
                .append(description)
                .append(isbn)
                .append(image)
                .append(publish)
                .append(publisher).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("title", title)
                .append("author", author)
                .append("genre", genre)
                .append("description", description)
                .append("isbn", isbn)
                .append("image", image)
                .append("publish", publish)
                .append("publisher", publisher)
                .toString();
    }
}

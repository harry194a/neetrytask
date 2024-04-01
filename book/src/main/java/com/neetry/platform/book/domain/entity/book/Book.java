package com.neetry.platform.book.domain.entity.book;

import com.neetry.platform.book.domain.entity.basic.AuditableBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by Harutyun Badeyan
 * Date: 24.03.24
 * Time: 16:32
 */
@Entity
public class Book extends AuditableBaseEntity {

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String genre;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String isbn;
    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    private String publish;
    @Column(nullable = false)
    private String publisher;

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(final String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(final String isbn) {
        this.isbn = isbn;
    }

    public String getImage() {
        return image;
    }

    public void setImage(final String image) {
        this.image = image;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(final String publish) {
        this.publish = publish;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(final String publisher) {
        this.publisher = publisher;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof final Book book)) return false;

        return new EqualsBuilder().appendSuper(super.equals(o))
                .append(getTitle(), book.getTitle())
                .append(getAuthor(), book.getAuthor())
                .append(getGenre(), book.getGenre())
                .append(getDescription(), book.getDescription())
                .append(getIsbn(), book.getIsbn())
                .append(getImage(), book.getImage())
                .append(getPublish(), book.getPublish())
                .append(getPublisher(), book.getPublisher()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).appendSuper(super.hashCode())
                .append(getTitle())
                .append(getAuthor())
                .append(getGenre())
                .append(getDescription())
                .append(getIsbn())
                .append(getImage())
                .append(getPublish())
                .append(getPublisher()).toHashCode();
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
                .append("id", id)
                .toString();
    }

    public Book() {
    }

    public Book(final String title,
                final String author, 
                final String genre, 
                final String description, 
                final String isbn, final String image, 
                final String publish, 
                final String publisher) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.description = description;
        this.isbn = isbn;
        this.image = image;
        this.publish = publish;
        this.publisher = publisher;
    }
}

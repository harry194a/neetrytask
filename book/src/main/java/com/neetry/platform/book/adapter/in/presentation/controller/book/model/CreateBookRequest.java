package com.neetry.platform.book.adapter.in.presentation.controller.book.model;

import com.neetry.platform.book.domain.model.book.CreateBookModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotEmpty;

/**
 * Created by Harutyun Badeyan
 * Date: 24.03.24
 * Time: 17:52
 */
public class CreateBookRequest {
    @NotEmpty(message = "required")
    private String title;
    @NotEmpty(message = "required")
    private String author;
    @NotEmpty(message = "required")
    private String genre;
    @NotEmpty(message = "required")
    private String description;
    @NotEmpty(message = "required")
    private String isbn;
    @NotEmpty(message = "required")
    private String image;
    @NotEmpty(message = "required")
    private String publish;
    @NotEmpty(message = "required")
    private String publisher;
    
    public CreateBookModel toCreateModel() {
        return new CreateBookModel(title,author,genre,description,isbn,image,publish,publisher);
    }

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

        if (!(o instanceof final CreateBookRequest that)) return false;

        return new EqualsBuilder()
                .append(getTitle(), that.getTitle())
                .append(getAuthor(), that.getAuthor())
                .append(getGenre(), that.getGenre())
                .append(getDescription(), that.getDescription())
                .append(getIsbn(), that.getIsbn())
                .append(getImage(), that.getImage())
                .append(getPublish(), that.getPublish())
                .append(getPublisher(), that.getPublisher()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getTitle())
                .append(getAuthor())
                .append(getGenre())
                .append(getDescription())
                .append(getIsbn())
                .append(getImage())
                .append(getPublish())
                .append(getPublisher())
                .toHashCode();
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

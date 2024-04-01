package com.neetry.platform.book.integration.domain.service.book;

import com.neetry.platform.book.adapter.exeption.ServiceException;
import com.neetry.platform.book.common.NeetryIntegrationTest;
import com.neetry.platform.book.common.Randomizer;
import com.neetry.platform.book.domain.entity.book.Book;
import com.neetry.platform.book.domain.model.PageQueryModel;
import com.neetry.platform.book.domain.model.book.CreateBookModel;
import com.neetry.platform.book.domain.model.book.UpdateBookModel;
import com.neetry.platform.book.domain.service.book.BookService;
import com.neetry.platform.book.integration.domain.service.book.toolkit.BookIntegrationTestingToolkit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by Harutyun Badeyan
 * Date: 01.04.24
 * Time: 01:20
 */

@NeetryIntegrationTest
class BookServiceIntegrationTest {

    @Autowired
    private BookIntegrationTestingToolkit testingToolkit;

    @Autowired
    private BookService bookService;

    @AfterEach
    void finish() {
        testingToolkit.cleanup();
    }

    @Test
    void should_successfullyGetBookById() {
        // GIVEN
        Book book = Randomizer.randomObject(Book.class);
        book.setId(null);
        var id = testingToolkit.persist(book).getId();
        
        // WHEN
        var result = bookService.getById(id);
        // THEN
        assertThat(result.id()).isNotNull().isEqualTo(id);
    }


    @Test
    void should_successfullyCreateBook() {
        // GIVEN
        CreateBookModel bookModel = Randomizer.randomObject(CreateBookModel.class);
        // WHEN
        var result = bookService.create(bookModel);
        // THEN
        assertThat(result.id()).isNotNull();
        assertThat(result.author()).isEqualTo(bookModel.author());
        assertThat(result.title()).isEqualTo(bookModel.title());
        assertThat(result.description()).isEqualTo(bookModel.description());
    }

    @Test
    void should_successfullyUpdateBook() {
        // GIVEN
        UpdateBookModel bookModel = Randomizer.randomObject(UpdateBookModel.class);
        Book book = Randomizer.randomObject(Book.class);
        book.setId(null);
        book = testingToolkit.persist(book);
        // WHEN
        var result = bookService.update(book.getId(), bookModel);
        // THEN
        assertThat(result.id()).isNotNull().isEqualTo(book.getId());
        assertThat(result.author()).isEqualTo(bookModel.author());
        assertThat(result.isbn()).isEqualTo(bookModel.isbn());
        assertThat(result.title()).isEqualTo(bookModel.title());
        assertThat(result.description()).isEqualTo(bookModel.description());
    }

    @Test
    void should_successfullyFetchAllBooks() {
        // GIVEN
        Book book1 = Randomizer.randomObject(Book.class);
        Book book2 = Randomizer.randomObject(Book.class);
        Book book3 = Randomizer.randomObject(Book.class);
        book1.setId(null);
        book2.setId(null);
        book3.setId(null);
        testingToolkit.persist(book1);
        testingToolkit.persist(book2);
        testingToolkit.persist(book3);
        // WHEN
        var result = bookService.getBooks(PageQueryModel.from(0, 10));
        // THEN
        assertThat(result).isNotNull().hasSize(3);
    }

    @Test
    void should_successfullyDeleteBook() {
        // GIVEN
        Book book = Randomizer.randomObject(Book.class);
        book.setId(null);
        var id = testingToolkit.persist(book).getId();
        // WHEN
        bookService.delete(id);
        // THEN
        assertThrows(ServiceException.class,() ->  bookService.getById(id));
    }
}

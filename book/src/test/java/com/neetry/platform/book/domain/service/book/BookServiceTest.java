package com.neetry.platform.book.domain.service.book;

import com.neetry.platform.book.adapter.out.repository.book.BookRepository;
import com.neetry.platform.book.common.Randomizer;
import com.neetry.platform.book.domain.entity.book.Book;
import com.neetry.platform.book.domain.model.PageQueryModel;
import com.neetry.platform.book.domain.model.book.CreateBookModel;
import com.neetry.platform.book.domain.model.book.UpdateBookModel;
import com.neetry.platform.book.domain.service.book.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Harutyun Badeyan
 * Date: 01.04.24
 * Time: 02:28
 */
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;
    
    @Test
    void should_SuccessfullyCreateBook() {
        // GIVEN
        var createBookModel = Randomizer.randomObject(CreateBookModel.class);
        ArgumentCaptor<Book> captor = ArgumentCaptor.captor();
        when(bookRepository.save(captor.capture())).thenAnswer(args -> args.getArguments()[0]);
        // WHEN
        var result = bookService.create(createBookModel);
        // THEN
        assertThat(result).isNotNull();
        var book = captor.getValue();
        assertThat(result.isbn()).isEqualTo(book.getIsbn());
        assertThat(result.author()).isEqualTo(book.getAuthor());
        assertThat(result.description()).isEqualTo(book.getDescription());
        verify(bookRepository).save(isA(Book.class));
    }
    
    @Test
    void should_SuccessfullyUpdateBook() {
        // GIVEN
        var updateBookModel = Randomizer.randomObject(UpdateBookModel.class);
        var book = Randomizer.randomObject(Book.class);
        long bookId = book.getId();
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(bookRepository.save(any())).thenAnswer(args -> args.getArguments()[0]);
        // WHEN
        var result = bookService.update(bookId, updateBookModel);
        // THEN
        assertThat(result).isNotNull();
        assertThat(result.isbn()).isEqualTo(updateBookModel.isbn());
        assertThat(result.author()).isEqualTo(updateBookModel.author());
        assertThat(result.description()).isEqualTo(updateBookModel.description());
        verify(bookRepository).findById(bookId);
        verify(bookRepository).save(isA(Book.class));
    }
    
    @Test
    void should_SuccessfullyDeleteBook() {
        // GIVEN
        var book = Randomizer.randomObject(Book.class);
        long bookId = book.getId();
        doNothing().when(bookRepository).deleteById(bookId);
        // WHEN
        bookService.delete(bookId);
        // THEN
        verify(bookRepository).deleteById(bookId);
    }
    
    @Test
    void should_SuccessfullySearchBooks() {
        // GIVEN
        var books = Randomizer.randomList(Book.class);
        PageQueryModel queryModel = PageQueryModel.from(0, 1);
        var booksPage = new PageImpl(books, queryModel.getPageable(), 100);
        when(bookRepository.findAll(queryModel.getPageable())).thenReturn(booksPage);
        // WHEN
        var result = bookService.getBooks(queryModel);
        // THEN
        verify(bookRepository).findAll(queryModel.getPageable());
        assertThat(result).hasSize(books.size());
    }
}

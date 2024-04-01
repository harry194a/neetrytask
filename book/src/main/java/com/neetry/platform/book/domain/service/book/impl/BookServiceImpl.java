package com.neetry.platform.book.domain.service.book.impl;

import com.neetry.platform.book.adapter.exeption.ErrorCode;
import com.neetry.platform.book.adapter.exeption.ServiceException;
import com.neetry.platform.book.adapter.out.repository.book.BookRepository;
import com.neetry.platform.book.domain.entity.book.Book;
import com.neetry.platform.book.domain.model.PageQueryModel;
import com.neetry.platform.book.domain.model.book.BookModel;
import com.neetry.platform.book.domain.model.book.CreateBookModel;
import com.neetry.platform.book.domain.model.book.UpdateBookModel;
import com.neetry.platform.book.domain.service.book.BookService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

/**
 * Created by Harutyun Badeyan
 * Date: 24.03.24
 * Time: 20:40
 */
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public BookModel create(final CreateBookModel model) {
        Assert.notNull(model, "Request must not be null");
        return BookModel.from(repository.save(model.toEntity()));
    }

    @Override
    @Transactional
    public BookModel update(final Long id, final UpdateBookModel request) {
        Assert.notNull(request, "Request must not be null");
        Assert.notNull(id, "Id cannot be null");
        Book book = getEntityById(id);
        book = repository.save(request.toEntity(book));
        return BookModel.from(book);
    }

    @Override
    public BookModel getById(final Long id) {
        Assert.notNull(id, "Id cannot be null");
        Book book = getEntityById(id);
        return BookModel.from(book);
    }

    @Override
    @Transactional
    public Page<BookModel> getPreferredBooksByUserId(List<String> genres, List<String> authors, PageQueryModel page) {
        Page<Book> books = repository.getBooksByPreference(genres, authors, page.getPageable());
        return new PageImpl(
                BookModel.from(books.getContent()),
                books.getPageable(),
                books.getTotalElements()
        );
    }

    @Override
    @Transactional
    public void delete(final Long id) {
        Assert.notNull(id, "Id cannot be null");
        repository.deleteById(id);
    }

    @Override
    public Page<BookModel> getBooks(PageQueryModel page) {
        Page<Book> bookPage = repository.findAll(page.getPageable());
        return bookPage.map(BookModel::from);
    }

    public Book getEntityById(final Long id) {
        Assert.notNull(id, "Id cannot be null");
        Optional<Book> book = repository.findById(id);
        if (book.isEmpty()) {
            throw new ServiceException(String.format("Book with id %s not exists", id), ErrorCode.NOT_EXIST);
        }
        return book.get();
    }
}

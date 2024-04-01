package com.neetry.platform.book.domain.service.book;

import com.neetry.platform.book.domain.model.PageQueryModel;
import com.neetry.platform.book.domain.model.book.BookModel;
import com.neetry.platform.book.domain.model.book.CreateBookModel;
import com.neetry.platform.book.domain.model.book.UpdateBookModel;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Harutyun Badeyan
 * Date: 24.03.24
 * Time: 20:01
 */
public interface BookService {
    BookModel create(final CreateBookModel request);

    BookModel update(final Long id, final UpdateBookModel request);

    BookModel getById(final Long id);

    Page<BookModel> getPreferredBooksByUserId(List<String> genres, List<String> authors, PageQueryModel pageQuery);

    void delete(final Long id);

    Page<BookModel> getBooks(PageQueryModel pageQuery);
}

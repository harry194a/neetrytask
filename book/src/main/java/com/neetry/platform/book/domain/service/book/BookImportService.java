package com.neetry.platform.book.domain.service.book;

import com.neetry.platform.book.adapter.out.repository.book.FakeItBookRepository;
import com.neetry.platform.book.domain.entity.book.Book;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Harutyun Badeyan
 * Date: 31.03.24
 * Time: 22:19
 */
@Service
public class BookImportService {
    
    private final FakeItBookRepository fakeItBookRepository;

    public BookImportService(FakeItBookRepository fakeItBookRepository) {
        this.fakeItBookRepository = fakeItBookRepository;
    }
    
    public List<Book> getAll() {
        return fakeItBookRepository.getAll();
    }
}

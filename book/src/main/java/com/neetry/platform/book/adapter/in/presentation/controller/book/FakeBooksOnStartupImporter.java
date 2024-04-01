package com.neetry.platform.book.adapter.in.presentation.controller.book;

import com.neetry.platform.book.adapter.out.repository.book.BookRepository;
import com.neetry.platform.book.domain.service.book.BookImportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by Harutyun Badeyan
 * Date: 31.03.24
 * Time: 22:25
 */
@Component
@Profile("!test")
public class FakeBooksOnStartupImporter {
    
    private final Logger logger = LoggerFactory.getLogger(FakeBooksOnStartupImporter.class);
    
    private final BookImportService bookImportService;
    private final BookRepository bookRepository;

    public FakeBooksOnStartupImporter(BookImportService bookImportService, BookRepository bookRepository) {
        this.bookImportService = bookImportService;
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationStartedEvent.class)
    void importFakeBooks() {
        logger.info("Import books from fake book repository.");
        if (bookRepository.existsByIdNotNull()) {
            logger.info("BookRepository is not empty, skipping import");
        }
        var books = bookImportService.getAll();
        logger.info("Importing books. Size is - {}", books.size());
        bookRepository.saveAll(books);
    }
}

package com.neetry.platform.book.integration.domain.service.book.toolkit;

import com.neetry.platform.book.domain.entity.book.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Created by Harutyun Badeyan
 * Date: 01.04.24
 * Time: 01:22
 */
@Component
public class BookIntegrationTestingToolkit {

    @PersistenceContext
    private EntityManager entityManager;

    private final TransactionTemplate transactionTemplate;

    public BookIntegrationTestingToolkit(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    public void cleanup() {
        transactionTemplate.execute((it) -> {
            entityManager.createQuery("delete from Book book where book.id is not null").executeUpdate();
            entityManager.flush();
            return null;
        });
    }

    public Book persist(Book book) {
        return transactionTemplate.execute(it -> {
            entityManager.persist(book);
            entityManager.flush();
            return book;
        });
    }

    public Book getById(final Long id) {
        return entityManager.createQuery("select book from Book book where book.id=:id", Book.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}

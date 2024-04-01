package com.neetry.platform.book.domain.service.preference.impl;

import com.neetry.platform.book.adapter.exeption.ErrorCode;
import com.neetry.platform.book.adapter.exeption.ServiceException;
import com.neetry.platform.book.adapter.in.presentation.controller.preference.model.CreatePreferenceRequest;
import com.neetry.platform.book.adapter.out.repository.preference.PreferenceRepository;
import com.neetry.platform.book.domain.entity.preference.Preference;
import com.neetry.platform.book.domain.model.PageQueryModel;
import com.neetry.platform.book.domain.model.book.BookModel;
import com.neetry.platform.book.domain.model.preference.PreferenceModel;
import com.neetry.platform.book.domain.service.book.BookService;
import com.neetry.platform.book.domain.service.preference.PreferenceService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PreferenceServiceImpl implements PreferenceService {
    private final BookService bookService;
    private final PreferenceRepository repository;

    public PreferenceServiceImpl(BookService bookService, PreferenceRepository repository) {
        this.bookService = bookService;
        this.repository = repository;
    }

    @Override
    @Transactional
    public List<PreferenceModel> create(final CreatePreferenceRequest model) {
        List<Preference> response = new ArrayList<>();

        BookModel bookModel = bookService.getById(model.getBookId());

        Preference authorPreference = new Preference();
        Preference genrePreference = new Preference();

        Preference existingAuthorPreference = repository.findByUserIdAndPreferenceTypeAndValue(model.getUserId(), "author", bookModel.author());
        Preference existingGenrePreference = repository.findByUserIdAndPreferenceTypeAndValue(model.getUserId(), "genre", bookModel.genre());

        if (existingAuthorPreference != null) {
            existingAuthorPreference.setQuantity(existingAuthorPreference.getQuantity() + 1);
            response.add(repository.save(existingAuthorPreference));
        } else {
            authorPreference.setUserId(model.getUserId());
            authorPreference.setPreferenceType("author");
            authorPreference.setValue(bookModel.author());
            authorPreference.setQuantity(1L);
            response.add(repository.save(authorPreference));
        }

        if (existingGenrePreference != null) {
            existingGenrePreference.setQuantity(existingGenrePreference.getQuantity() + 1);
            response.add(repository.save(existingGenrePreference));
        } else {
            genrePreference.setUserId(model.getUserId());
            genrePreference.setPreferenceType("genre");
            genrePreference.setValue(bookModel.genre());
            genrePreference.setQuantity(1L);
            response.add(repository.save(genrePreference));
        }

        return PreferenceModel.from(response);
    }

    @Override
    @Transactional
    public PreferenceModel getById(final Long id) {
        Assert.notNull(id, "Id cannot be null");
        Preference preference = getEntityById(id);
        return PreferenceModel.from(preference);
    }

    @Override
    @Transactional
    public void delete(final Long id) {
        Assert.notNull(id, "Id cannot be null");
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public List<String> getAllAuthorsByUserId(Long id) {
        Assert.notNull(id, "Id cannot be null");
        return repository.getAllAuthorsByUserId(id);
    }

    @Override
    @Transactional
    public List<String> getAllGenresByUserId(Long id) {
        Assert.notNull(id, "Id cannot be null");
        return repository.getAllGenresByUserId(id);
    }

    @Transactional
    @Override
    public Page<BookModel> getPreferredBooksByUserId(Long id, PageQueryModel pageQuery) {
        Assert.notNull(id, "Id cannot be null");
        
        return bookService.getPreferredBooksByUserId(getAllGenresByUserId(id),getAllAuthorsByUserId(id), pageQuery);
    }

    public Preference getEntityById(final Long id) {
        Assert.notNull(id, "Id cannot be null");
        Optional<Preference> preference = repository.findById(id);
        if (preference.isEmpty()) {
            throw new ServiceException(String.format("Book with id %s not exists", id),
                    ErrorCode.NOT_EXIST);
        }
        return preference.get();
    }
}

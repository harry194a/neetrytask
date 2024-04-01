package com.neetry.platform.book.domain.service.preference;

import com.neetry.platform.book.adapter.in.presentation.controller.preference.model.CreatePreferenceRequest;
import com.neetry.platform.book.domain.model.PageQueryModel;
import com.neetry.platform.book.domain.model.book.BookModel;
import com.neetry.platform.book.domain.model.preference.PreferenceModel;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Harutyun Badeyan
 * Date: 28.03.24
 * Time: 17:25
 */
public interface PreferenceService {
    List<PreferenceModel> create(final CreatePreferenceRequest model);

    PreferenceModel getById(final Long id);
    
    void delete(final Long id);

    @Transactional
    List<String> getAllAuthorsByUserId(Long id);

    @Transactional
    List<String> getAllGenresByUserId(Long id);

    @Transactional
    Page<BookModel> getPreferredBooksByUserId(Long userId, PageQueryModel pageQuery);
}

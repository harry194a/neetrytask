package com.neetry.platform.book.adapter.in.presentation.controller.book;

import com.neetry.platform.book.adapter.in.presentation.controller.book.model.BookResponse;
import com.neetry.platform.book.adapter.in.presentation.controller.book.model.CreateBookRequest;
import com.neetry.platform.book.adapter.in.presentation.controller.book.model.UpdateBookRequest;
import com.neetry.platform.book.config.security.user.CurrentUser;
import com.neetry.platform.book.domain.model.PageQueryModel;
import com.neetry.platform.book.domain.model.book.BookModel;
import com.neetry.platform.book.domain.service.book.BookService;
import com.neetry.platform.book.domain.service.preference.PreferenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Harutyun Badeyan
 * Date: 27.03.24
 * Time: 17:55
 */

@RestController
@RequestMapping("api/books")
@Tag(name = "book")
public class BookController {
    
    private final CurrentUser currentUser;
    private final BookService service;
    private final PreferenceService preferenceService;

    public BookController(
            CurrentUser currentUser,
            BookService service,
            PreferenceService preferenceService) {
        this.currentUser = currentUser;
        this.service = service;
        this.preferenceService = preferenceService;
    }

    @PostMapping
    @Operation(summary = "create book")
    public ResponseEntity<BookResponse> create(
            @Valid @RequestBody CreateBookRequest request) {
        BookModel item = service.create(request.toCreateModel());
        BookResponse response = BookResponse.from(item);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "find book")
    public ResponseEntity<BookResponse> findById(
            @Valid @PathVariable("id") Long id) {
        BookModel item = service.getById(id);
        BookResponse response = BookResponse.from(item);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update book")
    public ResponseEntity<BookResponse> update(
            @RequestBody UpdateBookRequest request,
            @Valid @PathVariable("id") Long id) {
        BookModel item = service.update(id, request.toUpdateModel());
        BookResponse response = BookResponse.from(item);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete book")
    public void delete(@Valid @PathVariable("id") Long id) {
        this.service.delete(id);
    }

    @GetMapping("/search")
    @Operation(summary = "search")
    public Page<BookResponse> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<BookModel> bookPage = service.getBooks(PageQueryModel.from(page, size));
        return bookPage.map(BookResponse::from);
    }

    @GetMapping("/preferences")
    public Page<BookResponse> getPreferences(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Long userId = currentUser.getJwtUserInfo().getUserId();
        Page<BookModel> result = preferenceService.getPreferredBooksByUserId(userId, PageQueryModel.from(page, size));
        return result.map(BookResponse::from);
    }
}

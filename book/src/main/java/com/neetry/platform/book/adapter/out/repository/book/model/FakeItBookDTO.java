package com.neetry.platform.book.adapter.out.repository.book.model;

import java.time.LocalDate;

/**
 * Created by Harutyun Badeyan
 * Date: 31.03.24
 * Time: 21:48
 */
public record FakeItBookDTO(
        Long id, 
        String title,
        String author,
        String genre,
        String description,
        String isbn,
        String image,
        LocalDate published,
        String publisher
) {
}

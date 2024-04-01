package com.neetry.platform.book.adapter.out.repository.book.model;

import java.util.List;

/**
 * Created by Harutyun Badeyan
 * Date: 31.03.24
 * Time: 22:02
 */
public record FakeItBookPagedDTO(
        List<FakeItBookDTO> data
) {
}

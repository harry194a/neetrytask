package com.neetry.platform.book.domain.model.sale;

import com.neetry.platform.book.domain.entity.sale.Sale;

/**
 * Created by Harutyun Badeyan
 * Date: 27.03.24
 * Time: 19:46
 */
public record CreateSaleModel(Long userId, Long bookId) {
        public Sale toEntity () {
        Sale sale = new Sale();
        sale.setUserId(userId);
        sale.setBookId(bookId);
        return sale;
    }
}
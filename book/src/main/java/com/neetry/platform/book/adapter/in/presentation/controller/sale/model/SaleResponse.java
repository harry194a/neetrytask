package com.neetry.platform.book.adapter.in.presentation.controller.sale.model;

import com.neetry.platform.book.domain.entity.basic.ModelStatus;
import com.neetry.platform.book.domain.model.sale.SaleModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Harutyun Badeyan
 * Date: 24.03.24
 * Time: 17:53
 */
public record SaleResponse(
        long id,
        Long userId,
        Long bookId,
        LocalDateTime createdOn,
        LocalDateTime updatedOn,
        LocalDateTime deletedOn,
        ModelStatus status
) {
    public static SaleResponse from(
            final SaleModel sale) {
        return new SaleResponse(
                sale.id(),
                sale.userId(),
                sale.bookId(),
                sale.createdOn(),
                sale.updatedOn(),
                sale.deletedOn(),
                sale.status()

        );
    }

    public static List<SaleResponse> from(
            final List<SaleModel> saleModels) {
        return saleModels.stream().map(SaleResponse::from).collect(Collectors.toList());
    }
}

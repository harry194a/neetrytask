package com.neetry.platform.book.domain.model.sale;

import com.neetry.platform.book.domain.entity.basic.ModelStatus;
import com.neetry.platform.book.domain.entity.sale.Sale;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Harutyun Badeyan
 * Date: 27.03.24
 * Time: 19:46
 */
public record SaleModel(
        Long id,
        Long userId,
        Long bookId,
        LocalDateTime createdOn,
        LocalDateTime updatedOn,
        LocalDateTime deletedOn,
        ModelStatus status

) {
    public static SaleModel from(Sale sale) {
        return new SaleModel(
                sale.getId(),
                sale.getUserId(),
                sale.getBookId(),
                sale.getAudit().getCreatedOn(),
                sale.getAudit().getUpdatedOn(),
                sale.getAudit().getDeletedOn(),
                sale.getAudit().getStatus()
        );
    }

    public static List<SaleModel> from(
            final List<Sale> monitors) {
        return monitors.stream().map(SaleModel::from).collect(Collectors.toList());

    }

    @Override
    public Long userId() {
        return userId;
    }

    @Override
    public Long bookId() {
        return bookId;
    }

    @Override
    public LocalDateTime createdOn() {
        return createdOn;
    }

    @Override
    public LocalDateTime updatedOn() {
        return updatedOn;
    }

    @Override
    public LocalDateTime deletedOn() {
        return deletedOn;
    }

    @Override
    public ModelStatus status() {
        return status;
    }

    @Override
    public Long id() {
        return id;
    }
}


package com.neetry.platform.book.adapter.in.presentation.controller.sale.model;

import com.neetry.platform.book.domain.model.sale.UpdateSaleModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.NotEmpty;

/**
 * Created by Harutyun Badeyan
 * Date: 24.03.24
 * Time: 17:55
 */
public class UpdateSaleRequest {
    @NotEmpty(message = "required")
    private Long userId;
    @NotEmpty(message = "required")
    private Long bookId;

    public UpdateSaleModel toUpdateModel() {
        return new UpdateSaleModel(userId, bookId);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(final Long bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof final UpdateSaleRequest that)) return false;

        return new EqualsBuilder()
                .append(getUserId(), that.getUserId())
                .append(getBookId(), that.getBookId()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getUserId())
                .append(getBookId()).toHashCode();
    }
}

package com.neetry.platform.book.domain.model.sale;

import com.neetry.platform.book.domain.entity.sale.Sale;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by Harutyun Badeyan
 * Date: 27.03.24
 * Time: 19:47
 */
public class UpdateSaleModel {
    private final Long userId;
    private final Long bookId;

    public UpdateSaleModel(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public Sale toEntity(Sale sale) {
        sale.setBookId(bookId);
        sale.setUserId(userId);
        return sale;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof final UpdateSaleModel that)) return false;

        return new EqualsBuilder()
                .append(userId, that.userId)
                .append(bookId, that.bookId).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(userId)
                .append(bookId).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("bookId", bookId)
                .toString();
    }

    public Long getUserId() {
        return userId;
    }

    public Long getBookId() {
        return bookId;
    }
}

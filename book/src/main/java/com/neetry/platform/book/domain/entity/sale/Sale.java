package com.neetry.platform.book.domain.entity.sale;

import com.neetry.platform.book.domain.entity.basic.AuditableBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by Harutyun Badeyan
 * Date: 24.03.24
 * Time: 16:59
 */
@Entity
public class Sale extends AuditableBaseEntity {
    @Column(nullable = false)
    private Long userId;
    
    @Column(nullable = false)
    private Long bookId;

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

        if (!(o instanceof final Sale sale)) return false;

        return new EqualsBuilder().appendSuper(super.equals(o))
                .append(getUserId(), sale.getUserId())
                .append(getBookId(), sale.getBookId()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).appendSuper(super.hashCode())
                .append(getUserId())
                .append(getBookId()).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("bookId", bookId)
                .toString();
    }

    public Sale() {
    }
}

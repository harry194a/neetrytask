package com.neetry.platform.book.adapter.in.presentation.controller.preference.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotEmpty;

/**
 * Created by Harutyun Badeyan
 * Date: 28.03.24
 * Time: 16:58
 */
public class CreatePreferenceRequest {
    @NotEmpty(message = "required")
    private Long userId;
    @NotEmpty(message = "required")
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

        if (!(o instanceof final CreatePreferenceRequest that)) return false;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("bookId", bookId)
                .toString();
    }
}


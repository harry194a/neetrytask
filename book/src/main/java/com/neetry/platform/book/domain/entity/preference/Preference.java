package com.neetry.platform.book.domain.entity.preference;

import com.neetry.platform.book.domain.entity.basic.AuditableBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by Harutyun Badeyan
 * Date: 28.03.24
 * Time: 16:38
 */

@Entity
public class Preference extends AuditableBaseEntity {
    
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private String value;
    @Column(nullable = false)
    private String preferenceType;
    @Column(nullable = false)
    private Long quantity;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof final Preference that)) return false;

        return new EqualsBuilder().appendSuper(super.equals(o))
                .append(userId, that.userId)
                .append(value, that.value)
                .append(preferenceType, that.preferenceType)
                .append(quantity, that.quantity).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).appendSuper(super.hashCode())
                .append(userId)
                .append(value)
                .append(preferenceType)
                .append(quantity).toHashCode();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public String getPreferenceType() {
        return preferenceType;
    }

    public void setPreferenceType(final String preferenceType) {
        this.preferenceType = preferenceType;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(final Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("value", value)
                .append("preferenceType", preferenceType)
                .append("quantity", quantity)
                .append("id", id)
                .toString();
    }
}

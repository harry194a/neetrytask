package com.neetry.platform.book.domain.entity.basic;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/**
 * Created by Harutyun Badeyan
 * Date: 24.03.24
 * Time: 16:17
 */
@Embeddable
public final class Audit {

    @CreatedDate
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @LastModifiedDate
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @Column(name = "deleted_on")
    private LocalDateTime deletedOn;

    @Column(nullable = false, columnDefinition = "varchar(255)")
    @Enumerated(EnumType.STRING)
    private ModelStatus status = ModelStatus.ACTIVE;

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(final LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(final LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public LocalDateTime getDeletedOn() {
        return deletedOn;
    }

    public void setDeletedOn(final LocalDateTime deletedOn) {
        this.deletedOn = deletedOn;
    }

    public ModelStatus getStatus() {
        return status;
    }

    public void setStatus(final ModelStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (!(o instanceof final Audit audit)) return false;

        return new EqualsBuilder()
                .append(createdOn, audit.createdOn)
                .append(updatedOn, audit.updatedOn)
                .append(deletedOn, audit.deletedOn)
                .append(status, audit.status)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(createdOn)
                .append(updatedOn)
                .append(deletedOn)
                .append(status)
                .toHashCode();
    }
}


package com.neetry.platform.book.domain.model.preference;

import com.neetry.platform.book.domain.entity.basic.ModelStatus;
import com.neetry.platform.book.domain.entity.preference.Preference;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Harutyun Badeyan
 * Date: 28.03.24
 * Time: 17:12
 */
public record PreferenceModel(Long id,
                              Long userId,
                              String preferenceType,
                              String value,
                              Long quantity,
                              LocalDateTime createdOn,
                              LocalDateTime updatedOn,
                              LocalDateTime deletedOn,
                              ModelStatus status
) {
    public static PreferenceModel from(Preference preference) {
        return new PreferenceModel(
                preference.getId(),
                preference.getUserId(),
                preference.getPreferenceType(),
                preference.getValue(),
                preference.getQuantity(),
                preference.getAudit().getCreatedOn(),
                preference.getAudit().getUpdatedOn(),
                preference.getAudit().getDeletedOn(),
                preference.getAudit().getStatus()
        );
    }

    public static List<PreferenceModel> from(
            final List<Preference> monitors) {
        return monitors.stream().map(PreferenceModel::from).collect(Collectors.toList());
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public Long userId() {
        return userId;
    }

    @Override
    public String preferenceType() {
        return preferenceType;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public Long quantity() {
        return quantity;
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
}


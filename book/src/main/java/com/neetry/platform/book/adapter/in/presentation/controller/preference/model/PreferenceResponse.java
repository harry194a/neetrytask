package com.neetry.platform.book.adapter.in.presentation.controller.preference.model;

import com.neetry.platform.book.domain.entity.basic.ModelStatus;
import com.neetry.platform.book.domain.model.preference.PreferenceModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Harutyun Badeyan
 * Date: 28.03.24
 * Time: 17:03
 */
public record PreferenceResponse (
        long id,
        long userId,
        String preferenceType,
        String value,
        Long quantity,
        LocalDateTime createdOn,
        LocalDateTime updatedOn,
        LocalDateTime deletedOn,
        ModelStatus status
) {
    public static PreferenceResponse from(
            final PreferenceModel model) {
        return new PreferenceResponse(
                model.id(),
                model.userId(),
                model.preferenceType(),
                model.value(),
                model.quantity(),
                model.createdOn(),
                model.updatedOn(),
                model.deletedOn(),
                model.status()

        );
    }

    public static List<PreferenceResponse> from(
            final List<PreferenceModel> preferenceModels) {
        return preferenceModels.stream().map(PreferenceResponse::from).collect(Collectors.toList());
    }
}


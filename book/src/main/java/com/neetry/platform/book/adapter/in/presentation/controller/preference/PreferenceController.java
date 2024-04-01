package com.neetry.platform.book.adapter.in.presentation.controller.preference;

import com.neetry.platform.book.adapter.in.presentation.controller.preference.model.CreatePreferenceRequest;
import com.neetry.platform.book.adapter.in.presentation.controller.preference.model.PreferenceResponse;
import com.neetry.platform.book.config.security.user.CurrentUser;
import com.neetry.platform.book.domain.model.preference.PreferenceModel;
import com.neetry.platform.book.domain.service.preference.PreferenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Harutyun Badeyan
 * Date: 28.03.24
 * Time: 19:24
 */

@RestController
@RequestMapping("api/preferences")
@Tag(name = "preference")
public class PreferenceController {
    private final CurrentUser currentUser;
    private final PreferenceService service;

    @Autowired
    public PreferenceController(CurrentUser currentUser, PreferenceService service) {
        this.currentUser = currentUser;
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "create Preference")
    public ResponseEntity<List<PreferenceResponse>> create(
            @Valid @RequestBody Long bookId) {
        Long currentUserId = currentUser.getJwtUserInfo().getUserId();
        CreatePreferenceRequest request = new CreatePreferenceRequest(); 
        request.setBookId(bookId);
        request.setUserId(currentUserId);
        List<PreferenceModel> item = service.create(request);
        List<PreferenceResponse> response = PreferenceResponse.from(item);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "find Preference ")
    public ResponseEntity<PreferenceResponse> findById(@Valid @PathVariable("id") Long id) {
        PreferenceModel item = service.getById(id);
        PreferenceResponse response = PreferenceResponse.from(item);
        return ResponseEntity.ok(response);
    }
}
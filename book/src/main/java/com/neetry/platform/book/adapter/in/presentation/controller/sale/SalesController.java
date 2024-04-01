package com.neetry.platform.book.adapter.in.presentation.controller.sale;

import com.neetry.platform.book.adapter.in.presentation.controller.sale.model.CreateSaleRequest;
import com.neetry.platform.book.adapter.in.presentation.controller.sale.model.SaleResponse;
import com.neetry.platform.book.adapter.in.presentation.controller.sale.model.UpdateSaleRequest;
import com.neetry.platform.book.config.security.user.CurrentUser;
import com.neetry.platform.book.domain.model.PageQueryModel;
import com.neetry.platform.book.domain.model.sale.SaleModel;
import com.neetry.platform.book.domain.service.sale.SaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Harutyun Badeyan
 * Date: 27.03.24
 * Time: 17:55
 */

@RestController
@RequestMapping("api/sales")
@Tag(name = "sale")
public class SalesController {


    private final CurrentUser currentUser;

    private final SaleService service;

    public SalesController(CurrentUser currentUser, SaleService service) {
        this.currentUser = currentUser;
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "create sale")
    public ResponseEntity<SaleResponse> create(@Valid @RequestBody Long bookId) {
        Long userId = currentUser.getJwtUserInfo().getUserId();
        CreateSaleRequest request = new CreateSaleRequest();
        request.setBookId(bookId);
        request.setUserId(userId);
        SaleModel item = service.create(request.toCreateModel());
        SaleResponse response = SaleResponse.from(item);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "find sale ")
    public ResponseEntity<SaleResponse> findById(@Valid @PathVariable("id") Long id) {
        SaleModel item = service.getById(id);
        SaleResponse response = SaleResponse.from(item);
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    @Operation(summary = "update sale")
    public ResponseEntity<SaleResponse> update(
            @RequestBody UpdateSaleRequest request,
            @Valid @PathVariable("id") Long id
    ) {
        SaleModel item = service.update(id, request.toUpdateModel());
        SaleResponse response = SaleResponse.from(item);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/sales")
    public Page<SaleResponse> getPreferences(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<SaleModel> result = service.getSales(PageQueryModel.from(page, size));
        return result.map(SaleResponse::from);
    }
}

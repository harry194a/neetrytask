package com.neetry.platform.book.domain.service.sale;

import com.neetry.platform.book.domain.model.PageQueryModel;
import com.neetry.platform.book.domain.model.sale.CreateSaleModel;
import com.neetry.platform.book.domain.model.sale.SaleModel;
import com.neetry.platform.book.domain.model.sale.UpdateSaleModel;
import org.springframework.data.domain.Page;

/**
 * Created by Harutyun Badeyan
 * Date: 24.03.24
 * Time: 20:02
 */
public interface SaleService {
    SaleModel create(final CreateSaleModel model);

    SaleModel update(final Long id, final UpdateSaleModel request);

    SaleModel getById(final Long id);

    Page<SaleModel> getSales(PageQueryModel pageQueryModel);
}

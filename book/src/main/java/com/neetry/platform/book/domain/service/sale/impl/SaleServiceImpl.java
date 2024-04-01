package com.neetry.platform.book.domain.service.sale.impl;

import com.neetry.platform.book.adapter.exeption.ErrorCode;
import com.neetry.platform.book.adapter.exeption.ServiceException;
import com.neetry.platform.book.adapter.out.repository.sale.SaleRepository;
import com.neetry.platform.book.domain.entity.sale.Sale;
import com.neetry.platform.book.domain.model.PageQueryModel;
import com.neetry.platform.book.domain.model.sale.CreateSaleModel;
import com.neetry.platform.book.domain.model.sale.SaleModel;
import com.neetry.platform.book.domain.model.sale.UpdateSaleModel;
import com.neetry.platform.book.domain.service.book.BookService;
import com.neetry.platform.book.domain.service.sale.SaleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * Created by Harutyun Badeyan
 * Date: 24.03.24
 * Time: 20:02
 */
@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository repository;
    private final BookService bookService;

    public SaleServiceImpl(SaleRepository repository, BookService bookService) {
        this.repository = repository;
        this.bookService = bookService;
    }

    @Override
    @Transactional
    public SaleModel create(final CreateSaleModel model) {
        Assert.notNull(model, "Request must not be null");
        bookService.getById(model.bookId());
        return SaleModel.from(repository.save(model.toEntity()));
    }

    @Override
    @Transactional
    public SaleModel update(final Long id, final UpdateSaleModel request) {
        Assert.notNull(request, "Request must not be null");
        Assert.notNull(id, "Id cannot be null");
        bookService.getById(request.getBookId());
        Sale sale = getEntityById(id);
        sale = repository.save(request.toEntity(sale));
        return SaleModel.from(sale);
    }

    @Override
    @Transactional(readOnly = true)
    public SaleModel getById(final Long id) {
        Assert.notNull(id, "Id cannot be null");
        Sale sale = getEntityById(id);
        return SaleModel.from(sale);
    }

    @Override
    public Page<SaleModel> getSales(final PageQueryModel pageQueryModel) {
        Assert.notNull(pageQueryModel, "pageQueryModel cannot be null");
        Page<Sale> sales =  repository.findAll(pageQueryModel.getPageable());
        return new PageImpl<>(
                SaleModel.from(sales.getContent()),
                sales.getPageable(),
                sales.getTotalElements()
        );
    }
    
    @Transactional(readOnly = true)
    public Sale getEntityById(final Long id) {
        Assert.notNull(id, "Id cannot be null");
        Optional<Sale> sale = repository.findById(id);
        if (sale.isEmpty()) {
            throw new ServiceException(String.format("Sale with id %s not exists", id),
                    ErrorCode.NOT_EXIST);
        }
        return sale.get();
    }
}

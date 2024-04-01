package com.neetry.platform.book.domain.model;

import org.springframework.data.domain.Pageable;

/**
 * Created by Harutyun Badeyan
 * Date: 31.03.24
 * Time: 23:32
 */
public class PageQueryModel {

    private final static int DEFAULT_PAGE = 0;
    private final static int DEFAULT_PAGE_SIZE = 25;

    private final Pageable pageable;

    private PageQueryModel(int size, int page) {
        this.pageable = Pageable.ofSize(size).withPage(page);
    }

    public static PageQueryModel from(Integer page, Integer size) {
        
        return new PageQueryModel(
                size == null ? DEFAULT_PAGE_SIZE : size,
                page == null ? DEFAULT_PAGE : page
        );
    }

    public Pageable getPageable() {
        return this.pageable;
    }
    
}

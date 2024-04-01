package com.neetry.platform.book.adapter.out.repository.sale;

import com.neetry.platform.book.domain.entity.sale.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Harutyun Badeyan
 * Date: 24.03.24
 * Time: 19:25
 */
@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    
}

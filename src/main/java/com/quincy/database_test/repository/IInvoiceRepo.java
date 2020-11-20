package com.quincy.database_test.repository;

import com.quincy.database_test.model.Invoice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IInvoiceRepo extends CrudRepository<Invoice, Long> {
    @Query("select i from Invoice i join fetch i.client c join fetch i.lines l join fetch l.product where i.id=?1")
    public Invoice fetchByIdWithClientWithInvoiceLineWithProduct(Long id);

}

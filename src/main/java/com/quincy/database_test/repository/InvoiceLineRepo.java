package com.quincy.database_test.repository;

import com.quincy.database_test.model.InvoiceLine;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InvoiceLineRepo extends CrudRepository<InvoiceLine, Long> {
    Optional<InvoiceLine> findById(Long id);
}

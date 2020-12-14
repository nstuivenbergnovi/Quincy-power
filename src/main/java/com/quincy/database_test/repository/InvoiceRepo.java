package com.quincy.database_test.repository;

import com.quincy.database_test.model.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepo extends CrudRepository<Invoice, Long> {


}

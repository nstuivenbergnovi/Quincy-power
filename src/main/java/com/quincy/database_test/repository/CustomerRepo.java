package com.quincy.database_test.repository;

import com.quincy.database_test.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Customer findByFirstName(String firstName);
    Optional<Customer> findById(int customerId);

}

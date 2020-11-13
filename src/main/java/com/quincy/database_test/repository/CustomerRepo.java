package com.quincy.database_test.repository;

import com.quincy.database_test.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    Customer findByFirstName(String firstName);
    Optional<Customer> findByCustomerId(int customerId);

   //@Query("SELECT new com.quincy.database_test.service.OrderResponse(c.lastName, c.firstName, p.productName) FROM Customer c JOIN c.product p")
   //public List<OrderResponse> getJoinInfo();
}

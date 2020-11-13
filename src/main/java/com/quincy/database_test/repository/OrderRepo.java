package com.quincy.database_test.repository;


import com.quincy.database_test.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Integer> {
}

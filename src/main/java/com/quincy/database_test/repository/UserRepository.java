package com.quincy.database_test.repository;

import com.quincy.database_test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Collection<User> findAllByUsername(String username);
    Optional<User> findByUsername(String username);
}

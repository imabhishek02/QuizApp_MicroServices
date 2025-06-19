package com.Auth.service.Repository;

import com.Auth.service.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepo extends JpaRepository<Customer,Integer> {
    Optional<Customer> findByUsername(String username);
}

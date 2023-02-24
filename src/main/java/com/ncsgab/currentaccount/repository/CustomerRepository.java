package com.ncsgab.currentaccount.repository;

import com.ncsgab.currentaccount.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

package com.ncsgab.currentaccount.repository;

import com.ncsgab.currentaccount.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}

package com.edian.edian_backend.repository;

import com.edian.edian_backend.common.AccountType;
import com.edian.edian_backend.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByIsActiveTrue();
    boolean existsByNumber(String number);
    List<Account> findByType(AccountType type);
    Account findByNumber(String number);
}

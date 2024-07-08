package com.edian.edian_backend.repository;

import com.edian.edian_backend.common.AccountType;
import com.edian.edian_backend.entity.Account;
import com.edian.edian_backend.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByIsActiveTrue();
    boolean existsByAccountNumber(Long accountNumber);
    List<Account> findByType(AccountType type);
    Account findByAccountNumber(Long number);
    @Query("SELECT MAX(a.accountNumber) FROM Account a")
    Optional<Long> findHighestAccountNumber();

}

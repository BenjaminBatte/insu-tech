package com.edian.edian_backend.repository;

import com.edian.edian_backend.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    Contract findByContractId(String contractId);


}
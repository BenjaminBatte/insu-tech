package com.edian.edian_backend.service;
import com.edian.edian_backend.dto.ContractDto;

import java.util.List;

public interface ContractService {
    ContractDto createContract(ContractDto dto);
    ContractDto getContractById(Long id);
    List<ContractDto> getAllContracts();
    ContractDto updateContract(Long id, ContractDto updatedDto);
    void deleteContract(Long id);
    List<ContractDto> addContracts(List<ContractDto> dtoList);
}

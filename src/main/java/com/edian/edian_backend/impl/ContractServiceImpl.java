package com.edian.edian_backend.impl;
import com.edian.edian_backend.dto.ContractDto;
import com.edian.edian_backend.entity.Contract;
import com.edian.edian_backend.exception.ResourceNotFoundException;
import com.edian.edian_backend.repository.AgentRepository;
import com.edian.edian_backend.repository.ContractRepository;
import com.edian.edian_backend.repository.NamedInsuredRepository;
import com.edian.edian_backend.repository.PolicyRepository;
import com.edian.edian_backend.service.ContractService;
import com.edian.edian_backend.utility.ContractServiceUtility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;
    private final PolicyRepository policyRepository;
    private final NamedInsuredRepository namedInsuredRepository;
    private final AgentRepository agentRepository;

    @Override
    public ContractDto createContract(ContractDto dto) {
        Contract contract = ContractServiceUtility.toContract(dto, policyRepository, namedInsuredRepository, agentRepository);
        Contract savedContract = contractRepository.save(contract);
        return ContractServiceUtility.toContractDto(savedContract);
    }

    @Override
    public ContractDto getContractById(Long id) {
        Contract contract = findContractById(id);
        return ContractServiceUtility.toContractDto(contract);
    }

    @Override
    public List<ContractDto> getAllContracts() {
        List<Contract> contracts = contractRepository.findAll();
        return contracts.stream()
                .map(ContractServiceUtility::toContractDto)
                .collect(Collectors.toList());
    }

    @Override
    public ContractDto updateContract(Long id, ContractDto updatedDto) {
        Contract contract = findContractById(id);
        ContractServiceUtility.updateContract(contract, updatedDto, policyRepository, namedInsuredRepository, agentRepository);
        Contract updatedContract = contractRepository.save(contract);
        return ContractServiceUtility.toContractDto(updatedContract);
    }

    @Override
    public void deleteContract(Long id) {
        Contract contract = findContractById(id);
        contractRepository.deleteById(id);
    }

    @Override
    public List<ContractDto> addContracts(List<ContractDto> dtoList) {
        List<Contract> contractList = dtoList.stream()
                .map(dto -> ContractServiceUtility.toContract(dto, policyRepository, namedInsuredRepository, agentRepository))
                .collect(Collectors.toList());

        List<Contract> savedContractList = contractRepository.saveAll(contractList);

        return savedContractList.stream()
                .map(ContractServiceUtility::toContractDto)
                .collect(Collectors.toList());
    }

    private Contract findContractById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract with id " + id + " not found"));
    }
}

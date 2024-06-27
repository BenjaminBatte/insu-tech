package com.edian.edian_backend.utility;

import com.edian.edian_backend.common.ContractStatus;
import com.edian.edian_backend.dto.ContractDto;
import com.edian.edian_backend.entity.Contract;
import com.edian.edian_backend.entity.Policy;

import com.edian.edian_backend.exception.ResourceNotFoundException;
import com.edian.edian_backend.repository.PolicyRepository;
import com.edian.edian_backend.repository.NamedInsuredRepository;
import com.edian.edian_backend.repository.AgentRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ContractServiceUtility {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS");

    public static ContractDto toContractDto(Contract contract) {
        ContractDto dto = new ContractDto();
        dto.setId(contract.getId());
        dto.setContractId(contract.getContractId());
        dto.setPremium(contract.getPremium());
        dto.setStartDate(contract.getStartDate());
        dto.setEndDate(contract.getEndDate());
        dto.setStatusId(contract.getStatus().getId());
        dto.setRenewalNumber(contract.getRenewalNumber());

        if (contract.getPolicy() != null) {
            dto.setPolicyNumber(contract.getPolicy().getPolicyNumber());
        }

        return dto;
    }
    private static Long generateRandomPolicyNumber() {
        long min = 1000000L; // Minimum value for 6-digit number (1000000)
        long max = 9999999L; // Maximum value for 6-digit number (9999999)
        return min + (long) (Math.random() * (max - min));
    }
    public static Contract toContract(ContractDto dto, PolicyRepository policyRepository) {
        Contract contract = new Contract();
        contract.setId(dto.getId());
        contract.setContractId(generateRandomPolicyNumber().toString());
        contract.setContractName("Contract: " + LocalDateTime.now().format(formatter));
        contract.setPremium(dto.getPremium());
        contract.setStartDate(dto.getStartDate());
        contract.setEndDate(dto.getEndDate());
        contract.setStatus(ContractStatus.IN_PROGRESS);
        contract.setRenewalNumber(dto.getRenewalNumber());

        setPolicy(dto, contract, policyRepository);

        return contract;
    }

    public static void updateContract(Contract contract, ContractDto dto, PolicyRepository policyRepository) {
        UpdateUtility.updateIfNotNull(contract::setContractId, dto.getContractId());
        UpdateUtility.updateIfNotNull(contract::setPremium, dto.getPremium());
        UpdateUtility.updateIfNotNull(contract::setStartDate, dto.getStartDate());
        UpdateUtility.updateIfNotNull(contract::setEndDate, dto.getEndDate());
        UpdateUtility.updateIfNotNull(contract::setStatus, ContractStatus.fromId(dto.getStatusId()));
        UpdateUtility.updateIfNotNull(contract::setRenewalNumber, dto.getRenewalNumber());

        setPolicy(dto, contract, policyRepository);
    }

    private static void setPolicy(ContractDto dto, Contract contract, PolicyRepository policyRepository) {
        if (dto.getPolicyNumber() != null) {
            Policy policy = policyRepository.findByPolicyNumber(dto.getPolicyNumber())
                    .orElseThrow(() -> new ResourceNotFoundException("Policy with number " + dto.getPolicyNumber() + " not found"));
            contract.setPolicy(policy);
        }
    }


}

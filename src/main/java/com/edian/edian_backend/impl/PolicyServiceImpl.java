package com.edian.edian_backend.impl;

import com.edian.edian_backend.dto.PolicyDto;
import com.edian.edian_backend.entity.Policy;
import com.edian.edian_backend.exception.ResourceNotFoundException;
import com.edian.edian_backend.repository.PolicyRepository;
import com.edian.edian_backend.repository.NamedInsuredRepository;
import com.edian.edian_backend.repository.AgentRepository;
import com.edian.edian_backend.service.PolicyService;
import com.edian.edian_backend.utility.PolicyServiceUtility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PolicyServiceImpl implements PolicyService {
    private final PolicyRepository policyRepository;

    @Override
    public PolicyDto createPolicy(PolicyDto dto) {
        Policy policy = PolicyServiceUtility.toPolicy(dto);
        Policy savedPolicy = policyRepository.save(policy);
        return PolicyServiceUtility.toPolicyDto(savedPolicy);
    }

    @Override
    public PolicyDto getPolicyById(Long id) {
        Policy policy = findPolicyById(id);
        return PolicyServiceUtility.toPolicyDto(policy);
    }

    @Override
    public List<PolicyDto> getAllPolicies() {
        List<Policy> policies = policyRepository.findAll();
        return policies.stream()
                .map(PolicyServiceUtility::toPolicyDto)
                .collect(Collectors.toList());
    }

    @Override
    public PolicyDto updatePolicy(Long id, PolicyDto updatedDto) {
        Policy policy = findPolicyById(id);
        PolicyServiceUtility.updatePolicy(policy, updatedDto);
        Policy updatedPolicy = policyRepository.save(policy);
        return PolicyServiceUtility.toPolicyDto(updatedPolicy);
    }

    @Override
    public void deletePolicy(Long id) {
        Policy policy = findPolicyById(id);
        policyRepository.deleteById(id);
    }

    @Override
    public List<PolicyDto> addPolicies(List<PolicyDto> dtoList) {
        List<Policy> policyList = dtoList.stream()
                .map(PolicyServiceUtility::toPolicy)
                .collect(Collectors.toList());

        List<Policy> savedPolicyList = policyRepository.saveAll(policyList);

        return savedPolicyList.stream()
                .map(PolicyServiceUtility::toPolicyDto)
                .collect(Collectors.toList());
    }

    private Policy findPolicyById(Long id) {
        return policyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Policy with id " + id + " not found"));
    }
    @Override
    public void deleteAllPolicies() {
        policyRepository.deleteAll();
    }
}

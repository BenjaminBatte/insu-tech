package com.edian.edian_backend.service;


import com.edian.edian_backend.dto.PolicyDto;

import java.util.List;

public interface PolicyService {
    PolicyDto createPolicy(PolicyDto dto);
    PolicyDto getPolicyById(Long id);
    List<PolicyDto> getAllPolicies();
    PolicyDto updatePolicy(Long id, PolicyDto updatedDto);
    void deletePolicy(Long id);
    List<PolicyDto> addPolicies(List<PolicyDto> dtoList);
}

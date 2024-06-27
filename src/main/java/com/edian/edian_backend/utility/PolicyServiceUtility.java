package com.edian.edian_backend.utility;

import com.edian.edian_backend.dto.PolicyDto;
import com.edian.edian_backend.entity.Policy;

import com.edian.edian_backend.repository.NamedInsuredRepository;
import org.springframework.stereotype.Component;

@Component
public class PolicyServiceUtility {

    public static PolicyDto toPolicyDto(Policy policy) {
        PolicyDto dto = new PolicyDto();
        dto.setId(policy.getId());
        dto.setPolicyNumber(policy.getPolicyNumber());

        dto.setBundleId(policy.getBundleId());

        return dto;
    }

    public static Policy toPolicy(PolicyDto dto) {
        Policy policy = new Policy();
        policy.setId(dto.getId());
        policy.setPolicyNumber(generateRandomPolicyNumber().toString());
        policy.setBundleId(dto.getBundleId());

        return policy;
    }
    private static Long generateRandomPolicyNumber() {
        long min = 10000L; // Minimum value for 6-digit number (10000)
        long max = 99999L; // Maximum value for 6-digit number (99999)
        return min + (long) (Math.random() * (max - min));
    }
    public static void updatePolicy(Policy policy, PolicyDto dto) {
        UpdateUtility.updateIfNotNull(policy::setPolicyNumber, dto.getPolicyNumber());
        UpdateUtility.updateIfNotNull(policy::setBundleId, dto.getBundleId());

    }

}

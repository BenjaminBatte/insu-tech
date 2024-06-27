package com.edian.edian_backend.utility;

import com.edian.edian_backend.dto.PolicyDto;
import com.edian.edian_backend.entity.Policy;
import com.edian.edian_backend.entity.NamedInsured;
import com.edian.edian_backend.entity.Agent;
import com.edian.edian_backend.exception.ResourceNotFoundException;
import com.edian.edian_backend.repository.NamedInsuredRepository;
import com.edian.edian_backend.repository.AgentRepository;
import org.springframework.stereotype.Component;

@Component
public class PolicyServiceUtility {

    public static PolicyDto toPolicyDto(Policy policy) {
        PolicyDto dto = new PolicyDto();
        dto.setId(policy.getId());
        dto.setPolicyNumber(policy.getPolicyNumber());

        if (policy.getNamedInsured() != null) {
            dto.setNamedInsuredId(policy.getNamedInsured().getId());
        }

        if (policy.getAgent() != null) {
            dto.setAgentId(policy.getAgent().getId());
        }

        dto.setBundleId(policy.getBundleId());

        return dto;
    }

    public static Policy toPolicy(PolicyDto dto, NamedInsuredRepository namedInsuredRepository, AgentRepository agentRepository) {
        Policy policy = new Policy();
        policy.setId(dto.getId());
        policy.setPolicyNumber(generateRandomPolicyNumber().toString());
        policy.setBundleId(dto.getBundleId());

        setNamedInsured(dto, policy, namedInsuredRepository);
        setAgent(dto, policy, agentRepository);

        return policy;
    }
    private static Long generateRandomPolicyNumber() {
        long min = 10000L; // Minimum value for 6-digit number (10000)
        long max = 99999L; // Maximum value for 6-digit number (99999)
        return min + (long) (Math.random() * (max - min));
    }
    public static void updatePolicy(Policy policy, PolicyDto dto, NamedInsuredRepository namedInsuredRepository, AgentRepository agentRepository) {
        UpdateUtility.updateIfNotNull(policy::setPolicyNumber, dto.getPolicyNumber());
        UpdateUtility.updateIfNotNull(policy::setBundleId, dto.getBundleId());

        setNamedInsured(dto, policy, namedInsuredRepository);
        setAgent(dto, policy, agentRepository);
    }

    private static void setNamedInsured(PolicyDto dto, Policy policy, NamedInsuredRepository namedInsuredRepository) {
        if (dto.getNamedInsuredId() != null) {
            NamedInsured namedInsured = namedInsuredRepository.findById(dto.getNamedInsuredId())
                    .orElseThrow(() -> new ResourceNotFoundException("Named insured with id " + dto.getNamedInsuredId() + " not found"));
            policy.setNamedInsured(namedInsured);
        }
    }

    private static void setAgent(PolicyDto dto, Policy policy, AgentRepository agentRepository) {
        if (dto.getAgentId() != null) {
            Agent agent = agentRepository.findById(dto.getAgentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Agent with id " + dto.getAgentId() + " not found"));
            policy.setAgent(agent);
        }
    }
}

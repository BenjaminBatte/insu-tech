package com.edian.edian_backend.utility;

import com.edian.edian_backend.dto.AgentDto;
import com.edian.edian_backend.entity.Agent;
import com.edian.edian_backend.entity.Address;
import com.edian.edian_backend.exception.ResourceNotFoundException;
import com.edian.edian_backend.repository.AddressRepository;

public class AgentServiceUtility {

    public static AgentDto toAgentDto(Agent agent) {
        AgentDto dto = new AgentDto();
        dto.setId(agent.getId());
        dto.setFirstName(agent.getFirstName());
        dto.setLastName(agent.getLastName());
        dto.setEmail(agent.getEmail());
        dto.setPhoneNumber(agent.getPhoneNumber());
        if (agent.getAddress() != null) {
            dto.setAddressId(agent.getAddress().getId());
        }
        return dto;
    }

    public static Agent toAgent(AgentDto dto, AddressRepository addressRepository) {
        Agent agent = new Agent();
        agent.setId(dto.getId());
        agent.setFirstName(dto.getFirstName());
        agent.setLastName(dto.getLastName());
        agent.setEmail(dto.getEmail());
        agent.setPhoneNumber(dto.getPhoneNumber());
        setAddress(dto, agent, addressRepository);
        return agent;
    }

    public static void updateAgent(Agent agent, AgentDto dto, AddressRepository addressRepository) {
        UpdateUtility.updateIfNotNull(agent::setFirstName, dto.getFirstName());
        UpdateUtility.updateIfNotNull(agent::setLastName, dto.getLastName());
        UpdateUtility.updateIfNotNull(agent::setEmail, dto.getEmail());
        UpdateUtility.updateIfNotNull(agent::setPhoneNumber, dto.getPhoneNumber());
        setAddress(dto, agent, addressRepository);
    }

    private static void setAddress(AgentDto dto, Agent agent, AddressRepository addressRepository) {
        if (dto.getAddressId() != null) {
            Address address = addressRepository.findById(dto.getAddressId())
                    .orElseThrow(() -> new ResourceNotFoundException("Address with id " + dto.getAddressId() + " not found"));
            agent.setAddress(address);
        }
    }
}

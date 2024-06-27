package com.edian.edian_backend.impl;

import com.edian.edian_backend.dto.AgentDto;
import com.edian.edian_backend.entity.Agent;
import com.edian.edian_backend.exception.ResourceNotFoundException;
import com.edian.edian_backend.repository.AgentRepository;
import com.edian.edian_backend.repository.AddressRepository;
import com.edian.edian_backend.service.AgentService;
import com.edian.edian_backend.utility.AgentServiceUtility;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AgentServiceImpl implements AgentService {
    private final AgentRepository agentRepository;
    private final AddressRepository addressRepository;

    @Override
    public AgentDto createAgent(AgentDto dto) {
        Agent agent = AgentServiceUtility.toAgent(dto, addressRepository);
        Agent savedAgent = agentRepository.save(agent);
        return AgentServiceUtility.toAgentDto(savedAgent);
    }

    @Override
    public AgentDto getAgentById(Long id) {
        Agent agent = findAgentById(id);
        return AgentServiceUtility.toAgentDto(agent);
    }

    @Override
    public List<AgentDto> getAllAgents() {
        List<Agent> agents = agentRepository.findAll();
        return agents.stream()
                .map(AgentServiceUtility::toAgentDto)
                .collect(Collectors.toList());
    }

    @Override
    public AgentDto updateAgent(Long id, AgentDto updatedAgent) {
        Agent agent = findAgentById(id);
        AgentServiceUtility.updateAgent(agent, updatedAgent, addressRepository);
        Agent updatedAgentObj = agentRepository.save(agent);
        return AgentServiceUtility.toAgentDto(updatedAgentObj);
    }

    @Override
    public List<AgentDto> addAgents(List<AgentDto> agentDtoList) {
        List<Agent> agents = agentDtoList.stream()
                .map(dto -> AgentServiceUtility.toAgent(dto, addressRepository))
                .collect(Collectors.toList());
        List<Agent> savedAgents = agentRepository.saveAll(agents);
        return savedAgents.stream()
                .map(AgentServiceUtility::toAgentDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAgent(Long id) {
        Agent agent = findAgentById(id);
        agentRepository.deleteById(id);
    }

    private Agent findAgentById(Long id) {
        return agentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agent with id " + id + " not found"));
    }
}

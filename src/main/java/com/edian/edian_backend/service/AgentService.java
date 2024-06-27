package com.edian.edian_backend.service;

import com.edian.edian_backend.dto.AgentDto;

import java.util.List;

public interface AgentService {
    AgentDto createAgent(AgentDto dto);
    AgentDto getAgentById(Long id);
    List<AgentDto> getAllAgents();
    AgentDto updateAgent(Long id, AgentDto updatedAgent);
    void deleteAgent(Long id);
    List<AgentDto> addAgents(List<AgentDto> agents);
}

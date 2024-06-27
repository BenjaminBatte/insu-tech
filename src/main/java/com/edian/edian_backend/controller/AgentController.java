package com.edian.edian_backend.controller;

import com.edian.edian_backend.dto.AgentDto;
import com.edian.edian_backend.service.AgentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agents")
@AllArgsConstructor
public class AgentController {
    private final AgentService agentService;

    @PostMapping
    public ResponseEntity<AgentDto> createAgent(@RequestBody AgentDto dto) {
        AgentDto savedAgent = agentService.createAgent(dto);
        return new ResponseEntity<>(savedAgent, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<AgentDto> getAgentById(@PathVariable("id") Long id) {
        AgentDto dto = agentService.getAgentById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<AgentDto>> getAllAgents() {
        List<AgentDto> agents = agentService.getAllAgents();
        return ResponseEntity.ok(agents);
    }

    @PutMapping("{id}")
    public ResponseEntity<AgentDto> updateAgent(@PathVariable("id") Long id, @RequestBody AgentDto updatedDto) {
        AgentDto dto = agentService.updateAgent(id, updatedDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAgent(@PathVariable("id") Long id) {
        agentService.deleteAgent(id);
        return ResponseEntity.ok("Agent deleted successfully");
    }
    @PostMapping("/batch")
    public ResponseEntity<List<AgentDto>> addAgents(@RequestBody List<AgentDto> agentDtoList) {
        List<AgentDto> savedAgents = agentService.addAgents(agentDtoList);
        return new ResponseEntity<>(savedAgents, HttpStatus.CREATED);
    }
}
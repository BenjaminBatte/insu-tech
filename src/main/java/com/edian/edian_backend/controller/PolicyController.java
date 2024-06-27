package com.edian.edian_backend.controller;

import com.edian.edian_backend.dto.PolicyDto;
import com.edian.edian_backend.service.PolicyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
@AllArgsConstructor
public class PolicyController {
    private final PolicyService policyService;

    @PostMapping
    public ResponseEntity<PolicyDto> createPolicy(@RequestBody PolicyDto dto) {
        PolicyDto savedPolicy = policyService.createPolicy(dto);
        return new ResponseEntity<>(savedPolicy, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<PolicyDto> getPolicyById(@PathVariable("id") Long id) {
        PolicyDto dto = policyService.getPolicyById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<PolicyDto>> getAllPolicies() {
        List<PolicyDto> policies = policyService.getAllPolicies();
        return ResponseEntity.ok(policies);
    }

    @PutMapping("{id}")
    public ResponseEntity<PolicyDto> updatePolicy(@PathVariable("id") Long id, @RequestBody PolicyDto updatedDto) {
        PolicyDto dto = policyService.updatePolicy(id, updatedDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePolicy(@PathVariable("id") Long id) {
        policyService.deletePolicy(id);
        return ResponseEntity.ok("Policy deleted successfully");
    }

    @PostMapping("/batch")
    public ResponseEntity<List<PolicyDto>> addPolicies(@RequestBody List<PolicyDto> dtoList) {
        List<PolicyDto> savedPolicyList = policyService.addPolicies(dtoList);
        return new ResponseEntity<>(savedPolicyList, HttpStatus.CREATED);
    }
    @DeleteMapping
    public ResponseEntity<String> deleteAllPolicies() {
        policyService.deleteAllPolicies();
        return ResponseEntity.ok("All policies deleted successfully");
    }
}

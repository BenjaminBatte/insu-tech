package com.edian.edian_backend.controller;
import com.edian.edian_backend.dto.ContractDto;
import com.edian.edian_backend.service.ContractService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contracts")
@AllArgsConstructor
public class ContractController {
    private final ContractService contractService;

    @PostMapping
    public ResponseEntity<ContractDto> createContract(@RequestBody ContractDto dto) {
        ContractDto savedContract = contractService.createContract(dto);
        return new ResponseEntity<>(savedContract, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ContractDto> getContractById(@PathVariable("id") Long id) {
        ContractDto dto = contractService.getContractById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<ContractDto>> getAllContracts() {
        List<ContractDto> contracts = contractService.getAllContracts();
        return ResponseEntity.ok(contracts);
    }

    @PutMapping("{id}")
    public ResponseEntity<ContractDto> updateContract(@PathVariable("id") Long id, @RequestBody ContractDto updatedDto) {
        ContractDto dto = contractService.updateContract(id, updatedDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteContract(@PathVariable("id") Long id) {
        contractService.deleteContract(id);
        return ResponseEntity.ok("Contract deleted successfully");
    }

    @PostMapping("/batch")
    public ResponseEntity<List<ContractDto>> addContracts(@RequestBody List<ContractDto> dtoList) {
        List<ContractDto> savedContractList = contractService.addContracts(dtoList);
        return new ResponseEntity<>(savedContractList, HttpStatus.CREATED);
    }
}

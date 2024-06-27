package com.edian.edian_backend.controller;
import com.edian.edian_backend.dto.NamedInsuredDto;
import com.edian.edian_backend.service.NamedInsuredService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/named-insureds")
@AllArgsConstructor
public class NamedInsuredController {
    private final NamedInsuredService namedInsuredService;

    @PostMapping
    public ResponseEntity<NamedInsuredDto> createNamedInsured(@RequestBody NamedInsuredDto dto) {
        NamedInsuredDto savedNamedInsured = namedInsuredService.createNamedInsured(dto);
        return new ResponseEntity<>(savedNamedInsured, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<NamedInsuredDto> getNamedInsuredById(@PathVariable("id") Long id) {
        NamedInsuredDto dto = namedInsuredService.getNamedInsuredById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<NamedInsuredDto>> getAllNamedInsureds() {
        List<NamedInsuredDto> namedInsureds = namedInsuredService.getAllNamedInsureds();
        return ResponseEntity.ok(namedInsureds);
    }

    @PutMapping("{id}")
    public ResponseEntity<NamedInsuredDto> updateNamedInsured(@PathVariable("id") Long id, @RequestBody NamedInsuredDto updatedDto) {
        NamedInsuredDto dto = namedInsuredService.updateNamedInsured(id, updatedDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteNamedInsured(@PathVariable("id") Long id) {
        namedInsuredService.deleteNamedInsured(id);
        return ResponseEntity.ok("Named Insured deleted successfully");
    }
    @PostMapping("/batch")
    public ResponseEntity<List<NamedInsuredDto>> addNamedInsureds(@RequestBody List<NamedInsuredDto> dtoList) {
        List<NamedInsuredDto> savedNamedInsuredList = namedInsuredService.addNamedInsureds(dtoList);
        return new ResponseEntity<>(savedNamedInsuredList, HttpStatus.CREATED);
    }
}
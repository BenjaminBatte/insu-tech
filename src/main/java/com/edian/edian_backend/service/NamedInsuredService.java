package com.edian.edian_backend.service;

import com.edian.edian_backend.dto.NamedInsuredDto;

import java.util.List;

public interface NamedInsuredService {
    NamedInsuredDto createNamedInsured(NamedInsuredDto dto);
    NamedInsuredDto getNamedInsuredById(Long id);
    List<NamedInsuredDto> getAllNamedInsureds();
    NamedInsuredDto updateNamedInsured(Long id, NamedInsuredDto updatedDto);
    void deleteNamedInsured(Long id);
    List<NamedInsuredDto> addNamedInsureds(List<NamedInsuredDto> dtoList);
}
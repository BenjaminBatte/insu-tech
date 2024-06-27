package com.edian.edian_backend.impl;

import com.edian.edian_backend.dto.NamedInsuredDto;
import com.edian.edian_backend.entity.NamedInsured;
import com.edian.edian_backend.exception.ResourceNotFoundException;
import com.edian.edian_backend.repository.AccountRepository;
import com.edian.edian_backend.repository.AddressRepository;
import com.edian.edian_backend.repository.NamedInsuredRepository;
import com.edian.edian_backend.service.NamedInsuredService;
import com.edian.edian_backend.utility.NamedInsuredServiceUtility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NamedInsuredServiceImpl implements NamedInsuredService {
    private final NamedInsuredRepository namedInsuredRepository;
    private final AccountRepository accountRepository;
    private final AddressRepository addressRepository;

    @Override
    public NamedInsuredDto createNamedInsured(NamedInsuredDto dto) {
        NamedInsured namedInsured = NamedInsuredServiceUtility.toNamedInsured(dto, addressRepository, accountRepository);
        NamedInsured savedNamedInsured = namedInsuredRepository.save(namedInsured);
        return NamedInsuredServiceUtility.toNamedInsuredDto(savedNamedInsured);
    }

    @Override
    public NamedInsuredDto getNamedInsuredById(Long id) {
        NamedInsured namedInsured = findNamedInsuredById(id);
        return NamedInsuredServiceUtility.toNamedInsuredDto(namedInsured);
    }

    @Override
    public List<NamedInsuredDto> getAllNamedInsureds() {
        List<NamedInsured> namedInsureds = namedInsuredRepository.findAll();
        return namedInsureds.stream()
                .map(NamedInsuredServiceUtility::toNamedInsuredDto)
                .collect(Collectors.toList());
    }

    @Override
    public NamedInsuredDto updateNamedInsured(Long id, NamedInsuredDto updatedDto) {
        NamedInsured namedInsured = findNamedInsuredById(id);
        NamedInsuredServiceUtility.updateNamedInsured(namedInsured, updatedDto, addressRepository, accountRepository);
        NamedInsured updatedNamedInsured = namedInsuredRepository.save(namedInsured);
        return NamedInsuredServiceUtility.toNamedInsuredDto(updatedNamedInsured);
    }

    @Override
    public void deleteNamedInsured(Long id) {
        NamedInsured namedInsured = findNamedInsuredById(id);
        namedInsuredRepository.deleteById(id);
    }
    @Override
    public List<NamedInsuredDto> addNamedInsureds(List<NamedInsuredDto> dtoList) {
        List<NamedInsured> namedInsuredList = dtoList.stream()
                .map(dto -> NamedInsuredServiceUtility.toNamedInsured(dto, addressRepository, accountRepository))
                .collect(Collectors.toList());

        List<NamedInsured> savedNamedInsuredList = namedInsuredRepository.saveAll(namedInsuredList);

        return savedNamedInsuredList.stream()
                .map(NamedInsuredServiceUtility::toNamedInsuredDto)
                .collect(Collectors.toList());
    }
    private NamedInsured findNamedInsuredById(Long id) {
        return namedInsuredRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NamedInsured with id " + id + " not found"));
    }
}

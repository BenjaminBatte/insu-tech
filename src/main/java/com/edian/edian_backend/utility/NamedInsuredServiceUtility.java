package com.edian.edian_backend.utility;

import com.edian.edian_backend.dto.NamedInsuredDto;
import com.edian.edian_backend.entity.Account;
import com.edian.edian_backend.entity.Address;
import com.edian.edian_backend.entity.NamedInsured;
import com.edian.edian_backend.exception.ResourceNotFoundException;
import com.edian.edian_backend.repository.AccountRepository;
import com.edian.edian_backend.repository.AddressRepository;
import org.springframework.stereotype.Component;


public class NamedInsuredServiceUtility {

    public static NamedInsuredDto toNamedInsuredDto(NamedInsured namedInsured) {
        NamedInsuredDto dto = new NamedInsuredDto();
        dto.setId(namedInsured.getId());
        dto.setFirstName(namedInsured.getFirstName());
        dto.setLastName(namedInsured.getLastName());
        dto.setEmail(namedInsured.getEmail());
        dto.setPhoneNumber(namedInsured.getPhoneNumber());

        if (namedInsured.getAddress() != null) {
            dto.setAddressId(namedInsured.getAddress().getId());
        }

        if (namedInsured.getAccount() != null) {
            dto.setAccountNumber(namedInsured.getAccount().getNumber());
        }

        return dto;
    }

    public static NamedInsured toNamedInsured(NamedInsuredDto dto, AddressRepository addressRepository, AccountRepository accountRepository) {
        NamedInsured namedInsured = new NamedInsured();
        namedInsured.setId(dto.getId());
        namedInsured.setFirstName(dto.getFirstName());
        namedInsured.setLastName(dto.getLastName());
        namedInsured.setEmail(dto.getEmail());
        namedInsured.setPhoneNumber(dto.getPhoneNumber());

        setAddress(dto, namedInsured, addressRepository);
        setAccount(dto, namedInsured, accountRepository);

        return namedInsured;
    }

    public static void updateNamedInsured(NamedInsured namedInsured, NamedInsuredDto dto, AddressRepository addressRepository, AccountRepository accountRepository) {
        UpdateUtility.updateIfNotNull(namedInsured::setFirstName, dto.getFirstName());
        UpdateUtility.updateIfNotNull(namedInsured::setLastName, dto.getLastName());
        UpdateUtility.updateIfNotNull(namedInsured::setEmail, dto.getEmail());
        UpdateUtility.updateIfNotNull(namedInsured::setPhoneNumber, dto.getPhoneNumber());
        setAddress(dto, namedInsured, addressRepository);
        setAccount(dto, namedInsured, accountRepository);
    }

    private static void setAddress(NamedInsuredDto dto, NamedInsured namedInsured, AddressRepository addressRepository) {
        if (dto.getAddressId() != null) {
            Address address = addressRepository.findById(dto.getAddressId())
                    .orElseThrow(() -> new ResourceNotFoundException("Address with id " + dto.getAddressId() + " not found"));
            namedInsured.setAddress(address);
        }
    }

    private static void setAccount(NamedInsuredDto dto, NamedInsured namedInsured, AccountRepository accountRepository) {
        if (dto.getAccountNumber() != null) {
            Account account = accountRepository.findByNumber(dto.getAccountNumber());
            if (account == null) {
                throw new ResourceNotFoundException("Account with number " + dto.getAccountNumber() + " not found");
            }
            namedInsured.setAccount(account);
        }
    }
}

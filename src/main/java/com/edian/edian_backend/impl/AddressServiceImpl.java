package com.edian.edian_backend.impl;

import com.edian.edian_backend.dto.AddressDto;
import com.edian.edian_backend.entity.Address;
import com.edian.edian_backend.exception.ResourceNotFoundException;
import com.edian.edian_backend.repository.AddressRepository;
import com.edian.edian_backend.service.AddressService;
import com.edian.edian_backend.utility.AddressServiceUtility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Override
    public AddressDto createAddress(AddressDto dto) {
        Address address = AddressServiceUtility.toAddress(dto);
        Address savedAddress = addressRepository.save(address);
        return AddressServiceUtility.toAddressDto(savedAddress);
    }

    @Override
    public AddressDto getAddressById(Long id) {
        Address address = findAddressById(id);
        return AddressServiceUtility.toAddressDto(address);
    }
    @Override
    public List<AddressDto> addAddresses(List<AddressDto> addressDtoList) {
        List<Address> addresses = addressDtoList.stream()
                .map(AddressServiceUtility::toAddress)
                .collect(Collectors.toList());

        List<Address> savedAddresses = addressRepository.saveAll(addresses);

        return savedAddresses.stream()
                .map(AddressServiceUtility::toAddressDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<AddressDto> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream()
                .map(AddressServiceUtility::toAddressDto)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto updateAddress(Long id, AddressDto updatedAddress) {
        Address address = findAddressById(id);
        address.setStreet(updatedAddress.getStreet());
        address.setState(updatedAddress.getState());
        address.setZip(updatedAddress.getZip());
        address.setCountry(updatedAddress.getCountry());
        Address updatedObj = addressRepository.save(address);
        return AddressServiceUtility.toAddressDto(updatedObj);
    }

    @Override
    public void deleteAddress(Long id) {
        Address address = findAddressById(id);
        addressRepository.deleteById(id);
    }

    // Private helper method to find address by id and handle exception
    private Address findAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address with given id : " + id + " not found"));
    }
}

package com.edian.edian_backend.service;

import com.edian.edian_backend.dto.AddressDto;

import java.util.List;

public interface AddressService {
    AddressDto createAddress(AddressDto dto);
    AddressDto getAddressById(Long id);
  List<AddressDto> getAllAddresses();
  AddressDto updateAddress(Long id,AddressDto updatedAddress);
  void  deleteAddress(Long id);
    public List<AddressDto> addAddresses(List<AddressDto> addressDtoList);

    }

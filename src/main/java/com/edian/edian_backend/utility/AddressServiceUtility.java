package com.edian.edian_backend.utility;

import com.edian.edian_backend.dto.AddressDto;
import com.edian.edian_backend.entity.Address;

public class AddressServiceUtility {
    public static AddressDto toAddressDto(Address address){
        AddressDto dto= new AddressDto();
        dto.setId(address.getId());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setZip(address.getZip());
        dto.setCountry(address.getCountry());
        dto.setStreet(address.getStreet());
        return  dto;
    }

    public static Address toAddress(AddressDto dto){
        Address address= new Address();
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setZip(dto.getZip());
        address.setCountry(dto.getCountry());
        address.setStreet(dto.getStreet());
        return  address;
    }
}

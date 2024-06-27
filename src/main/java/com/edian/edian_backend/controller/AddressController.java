package com.edian.edian_backend.controller;

import com.edian.edian_backend.dto.AddressDto;
import com.edian.edian_backend.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@AllArgsConstructor
public class AddressController {
    private final AddressService addressService;
    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@RequestBody AddressDto dto){
        AddressDto savedAddress=addressService.createAddress(dto);
        return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<AddressDto>getAddressById(@PathVariable("id") Long id){
        AddressDto dto= addressService.getAddressById(id);
        return  ResponseEntity.ok(dto);
    }
    @GetMapping
    public  ResponseEntity<List<AddressDto>> getAllAddresses(){
        List<AddressDto>addresses=addressService.getAllAddresses();
        return  ResponseEntity.ok(addresses);
    }
    @PutMapping("{id}")
    public  ResponseEntity<AddressDto>updateAddress(@PathVariable("id") Long id, @RequestBody AddressDto updatedDto){
        AddressDto dto =addressService.updateAddress(id,updatedDto);
        return ResponseEntity.ok(dto);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String>deleteAddress(@PathVariable("id") Long id){
        addressService.deleteAddress(id);
        return ResponseEntity.ok("Address deleted successfully");
    }
    @PostMapping("/batch")
    public ResponseEntity<List<AddressDto>> addAddresses(@RequestBody List<AddressDto> addressDtoList) {
        List<AddressDto> savedAddresses = addressService.addAddresses(addressDtoList);
        return new ResponseEntity<>(savedAddresses, HttpStatus.CREATED);
    }
}

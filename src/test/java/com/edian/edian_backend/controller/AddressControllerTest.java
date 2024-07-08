package com.edian.edian_backend.controller;

import com.edian.edian_backend.dto.AddressDto;
import com.edian.edian_backend.service.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AddressController.class)
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;
    @Autowired
    private ObjectMapper objectMapper;
    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testGetAllAddresses() throws Exception {
        AddressDto address1 = new AddressDto(1L, "123 Street", "City", "State", "12345", "Country");
        AddressDto address2 = new AddressDto(2L, "456 Street", "City2", "State2", "67890", "Country2");
        List<AddressDto> addressList = Arrays.asList(address1, address2);

        when(addressService.getAllAddresses()).thenReturn(addressList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/address"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(addressList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(address1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].street").value(address1.getStreet()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(address2.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].street").value(address2.getStreet()));
    }
    @Test
    public void testUpdateAddress() throws Exception {
        Long id = 1L;
        AddressDto updatedDto = new AddressDto(id, "Updated Street", "Updated City", "Updated State", "98765", "Updated Country");
        AddressDto returnedDto = new AddressDto(id, updatedDto.getStreet(), updatedDto.getCity(), updatedDto.getState(), updatedDto.getZip(), updatedDto.getCountry());

        // Mocking the service call to update the address
        when(addressService.updateAddress(any(Long.class), any(AddressDto.class))).thenReturn(returnedDto);

        // Perform the PUT request to update the address
        mockMvc.perform(MockMvcRequestBuilders.put("/api/address/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verify the updated address
        when(addressService.getAddressById(id)).thenReturn(returnedDto); // Mocking the service call to fetch the updated address

        mockMvc.perform(MockMvcRequestBuilders.get("/api/address/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.street").value(updatedDto.getStreet()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value(updatedDto.getCity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.state").value(updatedDto.getState()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.zip").value(updatedDto.getZip()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.country").value(updatedDto.getCountry()));
    }


    @Test
    public void testDeleteAddress() throws Exception {
        Long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/address/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Address deleted successfully"));
    }

    @Test
    public void testAddAddresses() throws Exception {
        AddressDto address1 = new AddressDto(1L, "123 Street", "City", "State", "12345", "Country");
        AddressDto address2 = new AddressDto(2L, "456 Street", "City2", "State2", "67890", "Country2");
        List<AddressDto> addressList = Arrays.asList(address1, address2);

        when(addressService.addAddresses(any())).thenReturn(addressList);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/address/batch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[ { \"id\": 1, \"street\": \"123 Street\", \"city\": \"City\", \"state\": \"State\", \"zip\": \"12345\", \"country\": \"Country\" }, { \"id\": 2, \"street\": \"456 Street\", \"city\": \"City2\", \"state\": \"State2\", \"zip\": \"67890\", \"country\": \"Country2\" } ]"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(addressList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(address1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].street").value(address1.getStreet()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(address2.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].street").value(address2.getStreet()));
    }
}

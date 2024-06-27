package com.edian.edian_backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class NamedInsuredDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Long addressId;
    private String accountNumber;
}
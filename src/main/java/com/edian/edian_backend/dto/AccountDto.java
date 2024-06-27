package com.edian.edian_backend.dto;

import com.edian.edian_backend.common.AccountType;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private String number;
    private String name;
    private String type;
    private boolean isActive;
}

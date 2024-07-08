package com.edian.edian_backend.dto;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long accountNumber;
    private String name;
    private String type;
    private boolean isActive;
    private Long agentId;
    private Long namedInsuredId;
}

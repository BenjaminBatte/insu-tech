package com.edian.edian_backend.dto;

import com.edian.edian_backend.common.ContractType;
import lombok.*;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyDto {
    private Long id;
    private String policyNumber;
    private Long namedInsuredId;
    private Long agentId;
    private String bundleId;
    private List<ContractDto> contracts;
}

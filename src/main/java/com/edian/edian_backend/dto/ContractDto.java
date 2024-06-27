package com.edian.edian_backend.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractDto {
    private Long id;
    private String contractId;
    private Double premium;
    private Date startDate;
    private Date endDate;
    private String statusId;
    private Integer renewalNumber;
    private String policyNumber;
    private Long namedInsuredId;
    private Long agentId;
}
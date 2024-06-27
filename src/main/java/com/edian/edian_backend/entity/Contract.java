package com.edian.edian_backend.entity;

import com.edian.edian_backend.common.ContractStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contract_id", nullable = false, unique = true)
    private String contractId;

    @Column(nullable = false)
    private Double premium;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name = "contract_name", nullable = false)
    private String contractName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContractStatus status;

    @Column(name = "renewal_number", nullable = false)
    private Integer renewalNumber;

    @ManyToOne
    @JoinColumn(name = "policy_number", referencedColumnName = "policyNumber", nullable = false)
    private Policy policy;

}

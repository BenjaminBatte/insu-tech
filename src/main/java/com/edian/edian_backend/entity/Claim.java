package com.edian.edian_backend.entity;
import com.edian.edian_backend.common.ClaimStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "claims")
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String claimNumber;
    private String description;
    private Double amount;
    private LocalDateTime dateOfClaim;
    @Enumerated(EnumType.STRING)
    private
    ClaimStatus status;

    @ManyToOne
    @JoinColumn(name = "policy_id")
    private Policy policy;
}
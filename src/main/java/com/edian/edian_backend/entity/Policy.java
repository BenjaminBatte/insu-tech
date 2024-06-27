package com.edian.edian_backend.entity;

import com.edian.edian_backend.common.ContractType;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "policies")
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String policyNumber;
    @JoinColumn(name = "bundle_id")
    private String  bundleId;

    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contract> contracts;
}

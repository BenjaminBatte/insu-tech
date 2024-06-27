package com.edian.edian_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bundles")
public class Bundle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private Long bundleNumber;

    private String description;

    @OneToMany(mappedBy = "bundleId")
    private List<Policy> policies;

    public void addPolicy(Policy policy) {
        if (policies == null) {
            policies = new ArrayList<>();
        }
        policies.add(policy);
    }
}

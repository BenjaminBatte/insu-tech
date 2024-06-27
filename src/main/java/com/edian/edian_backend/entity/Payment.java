package com.edian.edian_backend.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private LocalDateTime paymentDate;
    private String paymentMethod; // e.g., Credit Card, Bank Transfer, Cash

    @ManyToOne
    @JoinColumn(name = "contract_number")
    private Contract  contract;
}
package com.edian.edian_backend.entity;

import com.edian.edian_backend.common.AccountType;
import com.edian.edian_backend.common.BooleanToYesNoConverter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private Long accountNumber;

    private String name;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Convert(converter = BooleanToYesNoConverter.class)
    private boolean isActive;

    @ManyToOne
    private Agent agent;

    @OneToOne
    @JoinColumn( unique = true, nullable = true)
    private NamedInsured namedInsured;
}

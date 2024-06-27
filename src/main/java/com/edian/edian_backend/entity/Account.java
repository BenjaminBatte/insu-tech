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
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String number;
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AccountType type; // Enum for Standard, Leasing
    @Convert(converter = BooleanToYesNoConverter.class)
    @Column(name = "account_status")
    private boolean isActive;
}

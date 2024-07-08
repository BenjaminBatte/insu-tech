package com.edian.edian_backend.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "named_insureds")
public class NamedInsured {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    @Column(name="last_name")
    private String lastName;
    private String email;
    @Column(name="phone_number")
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToOne(mappedBy = "namedInsured", cascade = CascadeType.ALL)
    private Account account;
}
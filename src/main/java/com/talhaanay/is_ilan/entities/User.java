package com.talhaanay.is_ilan.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "users")
@DynamicUpdate
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String location;

    private LocalDate birthDate;

    private String companyName;

    @Column(columnDefinition = "TEXT")
    private String aboutCompany;

    private String companyLocation;

    private String industry;

}

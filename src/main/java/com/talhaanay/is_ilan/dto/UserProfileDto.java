package com.talhaanay.is_ilan.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserProfileDto {
    private Long id;
    private String email;
    private String role;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String location;
    private LocalDate birthDate;
    private String companyName;
    private String companyLocation;
    private String aboutCompany;
    private String industry;
}
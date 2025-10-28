package com.talhaanay.is_ilan.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "employer_profiles")
@PrimaryKeyJoinColumn(name = "user_id")
public class Employer extends User {
    private String companyName;

    @Column(columnDefinition = "TEXT")
    private String aboutCompany;

    private String companyLocation;
    private String industry;

    public Employer() {
        super();
        this.setRole(com.talhaanay.is_ilan.enums.Role.IS_VEREN);
    }
}

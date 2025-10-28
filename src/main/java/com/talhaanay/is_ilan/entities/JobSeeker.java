package com.talhaanay.is_ilan.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "job_seeker_profiles")
@PrimaryKeyJoinColumn(name = "user_id")
public class JobSeeker extends User{

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String location;
    private LocalDate birthDate;

    public JobSeeker() {
        super();
        this.setRole(com.talhaanay.is_ilan.enums.Role.IS_ARAYAN);
    }
}

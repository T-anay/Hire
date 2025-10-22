package com.talhaanay.is_ilan.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JobPostingDto {
    private Long id;
    private String title;
    private String description;
    private String location;
    private String jobType;
    private String status;
    private LocalDateTime lastDateToApply;
    private LocalDateTime createdAt;
    private String employerCompanyName;
}
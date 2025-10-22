package com.talhaanay.is_ilan.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApplicationResponseDto {
    private Long id;
    private String status;
    private LocalDateTime applicationDate;
    private String coverLetter;
    private Long jobId;
    private String jobTitle;
    private Long candidateId;
    private String candidateFullName;
}
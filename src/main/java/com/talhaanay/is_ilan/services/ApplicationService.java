package com.talhaanay.is_ilan.services;

import com.talhaanay.is_ilan.dto.ApplicationCreateDto;
import com.talhaanay.is_ilan.dto.ApplicationResponseDto;

import java.util.List;

public interface ApplicationService {
    ApplicationResponseDto applyToJob(Long jobId, ApplicationCreateDto createDto);

    List<ApplicationResponseDto> getApplicationsForJob(Long jobId);

    List<ApplicationResponseDto> getMyApplications();
}

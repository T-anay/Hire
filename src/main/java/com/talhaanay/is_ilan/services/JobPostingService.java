package com.talhaanay.is_ilan.services;

import com.talhaanay.is_ilan.dto.JobPostingCreateDto;
import com.talhaanay.is_ilan.dto.JobPostingDto;
import com.talhaanay.is_ilan.dto.JobPostingUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobPostingService {
    JobPostingDto createJobPosting(JobPostingCreateDto createDto);

    Page<JobPostingDto> getAllOpenJobPostings(Pageable pageable);

    JobPostingDto getJobPostingById(Long jobId);

    JobPostingDto updateJobPosting(Long jobId, JobPostingUpdateDto updateDto);

    void deleteJobPosting(Long jobId);
}
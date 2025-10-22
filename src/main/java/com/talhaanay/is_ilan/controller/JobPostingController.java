package com.talhaanay.is_ilan.controller;

import com.talhaanay.is_ilan.dto.JobPostingCreateDto;
import com.talhaanay.is_ilan.dto.JobPostingDto;
import com.talhaanay.is_ilan.dto.JobPostingUpdateDto;
import com.talhaanay.is_ilan.services.JobPostingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/jobs")
public class JobPostingController {

    private final JobPostingService jobPostingService;

    @PostMapping
    public ResponseEntity<JobPostingDto> createJobPosting(
            @Valid @RequestBody JobPostingCreateDto createDto) {

        JobPostingDto createdJobDto = jobPostingService.createJobPosting(createDto);
        return new ResponseEntity<>(createdJobDto, HttpStatus.CREATED);
    }

    @GetMapping("/open")
    public ResponseEntity<Page<JobPostingDto>> getAllOpenJobPostings(
            Pageable pageable) {

        Page<JobPostingDto> openJobsPage = jobPostingService.getAllOpenJobPostings(pageable);
        return ResponseEntity.ok(openJobsPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPostingDto> getJobById(
            @PathVariable Long id) {

        JobPostingDto jobDto = jobPostingService.getJobPostingById(id);
        return ResponseEntity.ok(jobDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobPostingDto> updateJobPosting(
            @PathVariable Long id, @Valid @RequestBody JobPostingUpdateDto updateDto) {

        JobPostingDto updatedJobDto = jobPostingService.updateJobPosting(id, updateDto);
        return ResponseEntity.ok(updatedJobDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobPosting(
            @PathVariable Long id) {

        jobPostingService.deleteJobPosting(id);
        return ResponseEntity.noContent().build();
    }
}

package com.talhaanay.is_ilan.controller;

import com.talhaanay.is_ilan.dto.ApplicationCreateDto;
import com.talhaanay.is_ilan.dto.ApplicationResponseDto;
import com.talhaanay.is_ilan.services.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/{jobId}/apply")
    public ResponseEntity<ApplicationResponseDto> applyToJob(
            @PathVariable Long jobId,
            @Valid @RequestBody ApplicationCreateDto createDto) {

        ApplicationResponseDto newApplicationDto = applicationService.applyToJob(jobId, createDto);
        return new ResponseEntity<>(newApplicationDto, HttpStatus.CREATED);
    }

    @GetMapping("/{jobId}/applications")
    public ResponseEntity<List<ApplicationResponseDto>> getApplicationsForJob(@PathVariable Long jobId) {

        List<ApplicationResponseDto> applications = applicationService.getApplicationsForJob(jobId);
        return ResponseEntity.ok(applications);
    }
}
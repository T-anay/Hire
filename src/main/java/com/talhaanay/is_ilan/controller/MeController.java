package com.talhaanay.is_ilan.controller;

import com.talhaanay.is_ilan.dto.ApplicationResponseDto;
import com.talhaanay.is_ilan.services.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/me")
@RequiredArgsConstructor
public class MeController {

    private final ApplicationService applicationService;

    @GetMapping("/applications")
    public ResponseEntity<List<ApplicationResponseDto>> getMyApplications() {

        List<ApplicationResponseDto> myApplications = applicationService.getMyApplications();
        return ResponseEntity.ok(myApplications);
    }
}
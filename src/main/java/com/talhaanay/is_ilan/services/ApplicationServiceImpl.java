package com.talhaanay.is_ilan.services;

import com.talhaanay.is_ilan.dto.ApplicationCreateDto;
import com.talhaanay.is_ilan.dto.ApplicationResponseDto;
import com.talhaanay.is_ilan.entities.Application;
import com.talhaanay.is_ilan.entities.JobPosting;
import com.talhaanay.is_ilan.entities.User;
import com.talhaanay.is_ilan.exception.AccessDeniedException;
import com.talhaanay.is_ilan.exception.BadRequestException;
import com.talhaanay.is_ilan.exception.ResourceNotFoundException;
import com.talhaanay.is_ilan.mapper.ApplicationMapper;
import com.talhaanay.is_ilan.repository.ApplicationRepository;
import com.talhaanay.is_ilan.repository.JobPostingRepository;
import com.talhaanay.is_ilan.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobPostingRepository jobPostingRepository;
    private final ApplicationMapper applicationMapper;
    private static final String USER_NOT_FOUND = "Kullanıcı bulunamadı.";

    @Transactional
    @Override
    public ApplicationResponseDto applyToJob(Long jobId, ApplicationCreateDto createDto) {

        String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        User candidate = userRepository.findByEmail(authenticatedUserEmail)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));

        if (!"IS_ARAYAN".equalsIgnoreCase(candidate.getRole())) {
            throw new AccessDeniedException("Sadece İş Arayanlar başvuru yapabilir.");
        }

        JobPosting jobPosting = jobPostingRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("İlan bulunamadı: " + jobId));

        if (!"ACIK".equalsIgnoreCase(jobPosting.getStatus())) {
            throw new BadRequestException("Bu ilana Başvuru Yapamazsınız, ilan aktif değil. Mevcut Durum: \" + jobPosting.getStatus());");
        }

        Application newApplication = new Application();
        newApplication.setCandidate(candidate);
        newApplication.setJobPosting(jobPosting);
        newApplication.setCoverLetter(createDto.getCoverLetter());

        Application savedApplication = applicationRepository.save(newApplication);

        return applicationMapper.applicationToApplicationResponseDto(savedApplication);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ApplicationResponseDto> getApplicationsForJob(Long jobId) {

        String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User employer = userRepository.findByEmail(authenticatedUserEmail)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));

        JobPosting jobPosting = jobPostingRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("İlan bulunamadı. ID: " + jobId));

        if (!jobPosting.getEmployer().getId().equals(employer.getId())) {
            throw new AccessDeniedException("Bu ilana ait başvuruları görme yetkiniz yok.");
        }

        List<Application> applications = applicationRepository.findAllByJobPosting_Id(jobId);

        return applications.stream()
                .map(applicationMapper::applicationToApplicationResponseDto)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ApplicationResponseDto> getMyApplications() {

        String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User candidate = userRepository.findByEmail(authenticatedUserEmail)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));

        if (!"IS_ARAYAN".equalsIgnoreCase(candidate.getRole())) {
            throw new AccessDeniedException("Sadece İş Arayanlar başvurularını görebilir.");
        }

        List<Application> myApplications = applicationRepository.findAllByCandidate_Id(candidate.getId());

        return myApplications.stream()
                .map(applicationMapper::applicationToApplicationResponseDto)
                .toList();
    }
}
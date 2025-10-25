package com.talhaanay.is_ilan.services;

import com.talhaanay.is_ilan.dto.JobPostingCreateDto;
import com.talhaanay.is_ilan.dto.JobPostingUpdateDto;
import com.talhaanay.is_ilan.entities.Application;
import com.talhaanay.is_ilan.entities.JobPosting;
import com.talhaanay.is_ilan.entities.User;
import com.talhaanay.is_ilan.exception.AccessDeniedException;
import com.talhaanay.is_ilan.exception.ResourceNotFoundException;
import com.talhaanay.is_ilan.repository.ApplicationRepository;
import com.talhaanay.is_ilan.repository.JobPostingRepository;
import com.talhaanay.is_ilan.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.talhaanay.is_ilan.dto.JobPostingDto;
import com.talhaanay.is_ilan.mapper.JobPostingMapper;
import com.talhaanay.is_ilan.enums.Role;
import com.talhaanay.is_ilan.enums.JobStatus;

import java.util.List;

@RequiredArgsConstructor
@Service
public class JobPostingServiceImpl implements JobPostingService {

    private final JobPostingRepository jobPostingRepository;
    private final UserRepository userRepository;
    private final JobPostingMapper jobPostingMapper;
    private final ApplicationRepository applicationRepository;
    private static final String USER_NOT_FOUND = "Kullanıcı bulunamadı.";
    private static final String JOB_NOT_FOUND = "İlan bulunamadı: ";


    @Transactional
    @Override
    public JobPostingDto createJobPosting(JobPostingCreateDto createDto) {

        String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        User employer = userRepository.findByEmail(authenticatedUserEmail)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));

        if (employer.getRole() != Role.IS_VEREN) {
            throw new AccessDeniedException("Sadece İş Verenler yeni ilan oluşturabilir.");
        }

        JobPosting newJobPosting = new JobPosting();
        newJobPosting.setTitle(createDto.getTitle());
        newJobPosting.setDescription(createDto.getDescription());
        newJobPosting.setLocation(createDto.getLocation());
        newJobPosting.setLastDateToApply(createDto.getLastDateToApply());
        newJobPosting.setJobType(createDto.getJobType());
        newJobPosting.setStatus(JobStatus.ACIK);
        newJobPosting.setEmployer(employer);
        JobPosting savedJobPosting = jobPostingRepository.save(newJobPosting);
        return jobPostingMapper.jobPostingToJobPostingDto(savedJobPosting);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<JobPostingDto> getAllOpenJobPostings(Pageable pageable) {
        Page<JobPosting> openPostingsPage = jobPostingRepository.findAllByStatus(JobStatus.ACIK, pageable);
        return openPostingsPage.map(jobPostingMapper::jobPostingToJobPostingDto);
    }

    @Override
    @Transactional(readOnly = true)
    public JobPostingDto getJobPostingById(Long jobId) {

        JobPosting jobPosting = jobPostingRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException(JOB_NOT_FOUND + jobId));
        return jobPostingMapper.jobPostingToJobPostingDto(jobPosting);
    }

    @Transactional
    @Override
    public JobPostingDto updateJobPosting(Long jobId, JobPostingUpdateDto updateDto) {

        String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User employer = userRepository.findByEmail(authenticatedUserEmail)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));

        JobPosting jobPosting = jobPostingRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException(JOB_NOT_FOUND + jobId));

        if (!jobPosting.getEmployer().getId().equals(employer.getId())) {
            throw new AccessDeniedException("Bu ilanı güncelleme yetkiniz yok.");
        }

        jobPostingMapper.updateJobPostingFromDto(updateDto, jobPosting);
        JobPosting updatedJobPosting = jobPostingRepository.save(jobPosting);
        return jobPostingMapper.jobPostingToJobPostingDto(updatedJobPosting);
    }

    @Transactional
    @Override
    public void deleteJobPosting(Long jobId) {

        String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User employer = userRepository.findByEmail(authenticatedUserEmail)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));

        JobPosting jobPosting = jobPostingRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException(JOB_NOT_FOUND + jobId));

        if (!jobPosting.getEmployer().getId().equals(employer.getId())) {
            throw new AccessDeniedException("Bu ilanı silme yetkiniz yok.");
        }
        List<Application> applicationsToDelete = applicationRepository.findAllByJobPosting_Id(jobId);
        applicationRepository.deleteAll(applicationsToDelete);
        jobPostingRepository.delete(jobPosting);
    }
}
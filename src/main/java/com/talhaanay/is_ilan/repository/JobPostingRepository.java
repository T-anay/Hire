package com.talhaanay.is_ilan.repository;

import com.talhaanay.is_ilan.entities.JobPosting;
import com.talhaanay.is_ilan.enums.JobStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {

    Page<JobPosting> findAllByStatus(JobStatus status, Pageable pageable);
}

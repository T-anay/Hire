package com.talhaanay.is_ilan.repository;

import com.talhaanay.is_ilan.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findAllByJobPosting_Id(Long jobId);

    List<Application> findAllByCandidate_Id(Long candidateId);
}

package com.talhaanay.is_ilan.mapper;

import com.talhaanay.is_ilan.dto.JobPostingDto;
import com.talhaanay.is_ilan.dto.JobPostingUpdateDto;
import com.talhaanay.is_ilan.entities.JobPosting;
import com.talhaanay.is_ilan.enums.JobStatus;
import com.talhaanay.is_ilan.exception.BadRequestException;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface JobPostingMapper {

    @Mapping(source = "employer.companyName", target = "employerCompanyName")
    JobPostingDto jobPostingToJobPostingDto(JobPosting jobPosting);

    @Mapping(source = "status", target = "status", qualifiedByName = "stringToJobStatus")
    void updateJobPostingFromDto(JobPostingUpdateDto dto, @MappingTarget JobPosting entity);

    @Named("stringToJobStatus")
    default JobStatus stringToJobStatus(String statusString) {
        if (statusString == null) {
            return null;
        }
        try {
            return JobStatus.valueOf(statusString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Geçersiz durum değeri: " + statusString);
        }
    }
}
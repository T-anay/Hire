package com.talhaanay.is_ilan.mapper;

import com.talhaanay.is_ilan.dto.JobPostingDto;
import com.talhaanay.is_ilan.dto.JobPostingUpdateDto;
import com.talhaanay.is_ilan.entities.JobPosting;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface JobPostingMapper {

    @Mapping(source = "employer.companyName", target = "employerCompanyName")
    JobPostingDto jobPostingToJobPostingDto(JobPosting jobPosting);

    void updateJobPostingFromDto(JobPostingUpdateDto dto, @MappingTarget JobPosting entity);
}

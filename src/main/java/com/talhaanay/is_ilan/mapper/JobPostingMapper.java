package com.talhaanay.is_ilan.mapper;

import com.talhaanay.is_ilan.dto.JobPostingDto;
import com.talhaanay.is_ilan.dto.JobPostingUpdateDto;
import com.talhaanay.is_ilan.entities.Employer;
import com.talhaanay.is_ilan.entities.JobPosting;
import com.talhaanay.is_ilan.entities.User;
import com.talhaanay.is_ilan.enums.JobStatus;
import com.talhaanay.is_ilan.exception.BadRequestException;
import org.mapstruct.*;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface JobPostingMapper {

    @Mapping(source = "employer", target = "employerCompanyName", qualifiedByName = "employerToCompanyName")
    JobPostingDto jobPostingToJobPostingDto(JobPosting jobPosting);

    @Mapping(source = "status", target = "status", qualifiedByName = "stringToJobStatus")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "employer", ignore = true)
    void updateJobPostingFromDto(JobPostingUpdateDto dto, @MappingTarget JobPosting entity);

    @Named("employerToCompanyName")
    default String employerToCompanyName(User employer) {
        if (employer == null) {
            return null;
        }
        User actualUser = employer;
        if (actualUser instanceof HibernateProxy proxy) {
            LazyInitializer initializer = proxy.getHibernateLazyInitializer();
            actualUser = (User) initializer.getImplementation();
        }
        if (actualUser instanceof Employer emp) {
            return emp.getCompanyName();
        } else {
            return null;
        }
    }

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
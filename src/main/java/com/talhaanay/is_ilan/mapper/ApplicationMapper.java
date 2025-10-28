package com.talhaanay.is_ilan.mapper;

import com.talhaanay.is_ilan.dto.ApplicationResponseDto;
import com.talhaanay.is_ilan.entities.JobSeeker;
import com.talhaanay.is_ilan.entities.Application;
import com.talhaanay.is_ilan.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {

    @Mapping(source = "jobPosting.id", target = "jobId")
    @Mapping(source = "jobPosting.title", target = "jobTitle")
    @Mapping(source = "candidate.id", target = "candidateId")
    @Mapping(source = "candidate", target = "candidateFullName", qualifiedByName = "mapFullName")
    ApplicationResponseDto applicationToApplicationResponseDto(Application application);

    @Named("mapFullName")
    default String mapFullName(User candidate) {
        if (candidate == null) {
            return null;
        }
        User actualCandidate = candidate;
        if (actualCandidate instanceof HibernateProxy proxy) {
            LazyInitializer initializer = proxy.getHibernateLazyInitializer();
            actualCandidate = (User) initializer.getImplementation();
        }
        if (actualCandidate instanceof JobSeeker jobSeeker) {
            String firstName = jobSeeker.getFirstName() != null ? jobSeeker.getFirstName() : "";
            String lastName = jobSeeker.getLastName() != null ? jobSeeker.getLastName() : "";
            String fullName = (firstName + " " + lastName).trim();
            return fullName.isEmpty() ? null : fullName;
        } else {
            return null;
        }
    }
}
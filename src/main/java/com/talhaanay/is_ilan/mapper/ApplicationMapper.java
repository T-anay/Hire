package com.talhaanay.is_ilan.mapper;

import com.talhaanay.is_ilan.dto.ApplicationResponseDto;
import com.talhaanay.is_ilan.entities.Application;
import com.talhaanay.is_ilan.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

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
        String firstName = candidate.getFirstName() != null ? candidate.getFirstName() : "";
        String lastName = candidate.getLastName() != null ? candidate.getLastName() : "";
        return (firstName + " " + lastName).trim();
    }
}
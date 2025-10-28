package com.talhaanay.is_ilan.mapper;

import com.talhaanay.is_ilan.dto.RegisterIsArayanDto;
import com.talhaanay.is_ilan.dto.RegisterIsVerenDto;
import com.talhaanay.is_ilan.entities.Employer;
import com.talhaanay.is_ilan.entities.JobSeeker;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "email", source = "email")
    JobSeeker registerIsArayanDtoToJobSeeker(RegisterIsArayanDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "email", source = "email")
    Employer registerIsVerenDtoToEmployer(RegisterIsVerenDto dto);

}
package com.talhaanay.is_ilan.mapper;

import com.talhaanay.is_ilan.dto.RegisterIsArayanDto;
import com.talhaanay.is_ilan.dto.RegisterIsVerenDto;
import com.talhaanay.is_ilan.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "companyName", ignore = true)
    @Mapping(target = "companyLocation", ignore = true)
    @Mapping(target = "aboutCompany", ignore = true)
    @Mapping(target = "industry", ignore = true)
    User registerIsArayanDtoToUser(RegisterIsArayanDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "location", ignore = true)
    @Mapping(target = "birthDate", ignore = true)
    User registerIsVerenDtoToUser(RegisterIsVerenDto dto);

}
package com.snapppay.movies.mapper;

import com.snapppay.movies.domain.UserEntity;
import com.snapppay.movies.dto.RegisterDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UserMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "mobileNumber", source = "mobileNumber")
    UserEntity toUser(RegisterDto registerDto);
}
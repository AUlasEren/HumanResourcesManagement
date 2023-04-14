package com.hrm.mapper;

import com.hrm.dto.request.NewRegisterRequestDto;
import com.hrm.dto.response.RegisterResponseDto;
import com.hrm.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IAuthMapper {

    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);

    Auth toAuth(final NewRegisterRequestDto dto);
    RegisterResponseDto toRegisterResponseDto(final Auth auth);


}

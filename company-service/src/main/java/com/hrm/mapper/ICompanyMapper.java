package com.hrm.mapper;

import com.hrm.dto.response.CompanyDetailResponseDto;
import com.hrm.dto.response.CreateResponseDto;
import com.hrm.dto.response.UpdateResponseDto;
import com.hrm.repository.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICompanyMapper {
    ICompanyMapper INSTANCE = Mappers.getMapper(ICompanyMapper.class);

    Company toCompany(final CreateResponseDto dto);
    Company toCompany(final UpdateResponseDto dto);

    //Company toCompany(final CompanyDetailResponseDto dto);
}

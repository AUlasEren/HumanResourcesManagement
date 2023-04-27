package com.hrm.mapper;

import com.hrm.dto.request.NewCreateCompanyManagerRequestDto;
import com.hrm.dto.response.CompanyManagerDetailResponseDto;
import com.hrm.rabbitmq.model.RegisterCompanyManagerModel;
import com.hrm.repository.entity.CompanyManager;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICompanyManagerMapper {

    ICompanyManagerMapper INSTANCE = Mappers.getMapper(ICompanyManagerMapper.class);

    CompanyManager toCompanyManager(final NewCreateCompanyManagerRequestDto dto);
    RegisterCompanyManagerModel toModel(final CompanyManager companyManager);

    CompanyManagerDetailResponseDto toCompanyManagerDetailResponseDto(final CompanyManager companyManager);


}

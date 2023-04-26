package com.hrm.mapper;

import com.hrm.dto.request.NewCreateCompanyManagerRequestDto;
import com.hrm.dto.request.UserUpdateRequestDto;
import com.hrm.dto.response.UserDetailResponseDto;
import com.hrm.dto.response.UserSummaryResponseDto;
import com.hrm.rabbitmq.model.RegisterModel;
import com.hrm.repository.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAdminMapper {

    IAdminMapper INSTANCE = Mappers.getMapper(IAdminMapper.class);

    Admin toUser(final UserUpdateRequestDto dto);
    Admin toUser(final NewCreateCompanyManagerRequestDto dto);

    Admin toUser(final RegisterModel model);
    UserSummaryResponseDto toUserSummaryResponseDto(final Admin admin);

    UserDetailResponseDto toUserDetailResponseDto(final Admin admin);
}

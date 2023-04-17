package com.hrm.mapper;

import com.hrm.dto.request.NewCreateUserRequestDto;
import com.hrm.dto.request.UserUpdateRequestDto;
import com.hrm.dto.response.UserDetailResponseDto;
import com.hrm.dto.response.UserSummaryResponseDto;
import com.hrm.rabbitmq.model.RegisterModel;
import com.hrm.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    User toUser(final UserUpdateRequestDto dto);
    User toUser(final NewCreateUserRequestDto dto);

    User toUser(final RegisterModel model);
    UserSummaryResponseDto toUserSummaryResponseDto(final User user);

    UserDetailResponseDto toUserDetailResponseDto(final User user);
}

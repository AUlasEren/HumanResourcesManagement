package com.hrm.mapper;

import com.hrm.dto.request.NewCreateAdminRequestDto;
import com.hrm.rabbitmq.model.RegisterAdminModel;
import com.hrm.rabbitmq.producer.RegisterAdminProducer;
import com.hrm.repository.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAdminMapper {

    IAdminMapper INSTANCE = Mappers.getMapper(IAdminMapper.class);
    Admin toAdmin(final NewCreateAdminRequestDto dto);
    RegisterAdminModel toRegisterAdminModel(final Admin admin);

}

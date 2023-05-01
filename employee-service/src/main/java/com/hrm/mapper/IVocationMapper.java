package com.hrm.mapper;

import com.hrm.dto.request.NewCreateEmployeeRequestDto;
import com.hrm.dto.request.NewCreateVocationRequestDto;
import com.hrm.repository.entity.Employee;
import com.hrm.repository.entity.Vocation;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IVocationMapper {
    IVocationMapper INSTANCE = Mappers.getMapper(IVocationMapper.class);

    Vocation toVocation(final NewCreateVocationRequestDto dto);
}

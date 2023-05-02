package com.hrm.mapper;

import com.hrm.dto.request.NewCreateAdvanceRequestDto;
import com.hrm.dto.request.NewCreateExpenseRequestDto;
import com.hrm.repository.entity.Advance;
import com.hrm.repository.entity.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAdvanceMapper {

    IAdvanceMapper INSTANCE = Mappers.getMapper(IAdvanceMapper.class);

    Advance toAdvance(final NewCreateAdvanceRequestDto dto);

}

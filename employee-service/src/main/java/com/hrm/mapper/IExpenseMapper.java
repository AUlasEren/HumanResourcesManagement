package com.hrm.mapper;

import com.hrm.dto.request.NewCreateExpenseRequestDto;
import com.hrm.repository.entity.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IExpenseMapper {

    IExpenseMapper INSTANCE = Mappers.getMapper(IExpenseMapper.class);

    Expense toExpense(final NewCreateExpenseRequestDto dto);
}

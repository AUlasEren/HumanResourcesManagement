package com.hrm.dto.request;

import com.hrm.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.file.Path;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewCreateExpenseRequestDto {

    private String employeeId;
    private String expenseType;
    private Long expenseAmount;
    private String unitOfCurrency;
    private LocalDate requestDateOfExpense; // Harcama'nın yapılması planlanan tarih.
    private String path;
}

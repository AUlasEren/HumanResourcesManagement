package com.hrm.repository.entity;

import com.hrm.repository.enums.EStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class Expense extends BaseEntity{

    @Id
    private String id;
    private String employeeId;
    private String companyManagerId;
    private String expenseType;
    private Long expenseAmount;
    @Builder.Default
    private String unitOfCurrency = "TRY";
    @Builder.Default
    private EStatus expenseStatus= EStatus.PENDING;
    private LocalDate requestDateOfExpense; // Harcama'nın yapılması planlanan tarih.
    private LocalDate responseDateOfExpenseRequest; //Cevaplanma tarihi
    //Harcama tablosu içeren excel dosyası eklenmesi..

}

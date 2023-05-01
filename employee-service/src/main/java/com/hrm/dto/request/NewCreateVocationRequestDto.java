package com.hrm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewCreateVocationRequestDto {
    private String vocationType;
    private String employeeId;
    private LocalDate startOfVocationDate;
    private LocalDate endOfVocationDate;
}

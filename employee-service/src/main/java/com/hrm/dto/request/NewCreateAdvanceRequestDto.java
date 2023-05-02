package com.hrm.dto.request;

import com.hrm.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewCreateAdvanceRequestDto {

    private String employeeId;
    private Long advanceAmount;
    private String unitOfCurrency;
    private String descriptionOfAdvance;
    private String advanceType;

}

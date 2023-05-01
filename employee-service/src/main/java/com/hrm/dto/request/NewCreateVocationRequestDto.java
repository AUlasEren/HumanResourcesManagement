package com.hrm.dto.request;

import com.hrm.repository.enums.EVocationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewCreateVocationRequestDto {
    private String vocationType;
    private String employeeId;
    @Builder.Default
    private LocalDate startOfVocationDate = LocalDate.now();
    private LocalDate endOfVocationDate;
    private LocalDate responseOfRequestDate;
}

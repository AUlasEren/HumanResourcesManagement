package com.hrm.repository.entity;

import com.hrm.repository.enums.EStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class Vocation extends BaseEntity{
    @Id
    private String id;
    private String employeeId;
    private String vocationType;
    @Builder.Default
    private EStatus vocationStatus= EStatus.PENDING;
    private String companyManagerId;
    private LocalDate startOfVocationDate;
    private LocalDate endOfVocationDate;
    private LocalDate responseOfVocationRequestDate;
    private long vocationDuration;
}

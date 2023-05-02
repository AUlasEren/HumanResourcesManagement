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
public class Advance extends BaseEntity{

    @Id
    private String id;
    @Builder.Default
    private EStatus advanceStatus= EStatus.PENDING;
    private Long advanceAmount;
    private String unitOfCurrency;
    private String descriptionOfAdvance;
    private String advanceType;
    private LocalDate responseDateOfAdvanceRequest;



}

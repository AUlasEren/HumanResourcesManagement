package com.hrm.repository.entity;

import com.hrm.repository.enums.EStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Company extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String title;
    private String mersisNumber;
    private String taxNumber;
    private String TaxAdministration;
    private String image;
    private String phoneNumber;
    private String address;
    private String email;
    private int numberOfEmployees;
    private String foundationYear;
    private LocalDate contractStartDate;
    private LocalDate contractFinishDate;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EStatus status=EStatus.ACTIVE;
}

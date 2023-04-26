package com.hrm.repository.entity;

import com.hrm.repository.enums.EDepartment;
import com.hrm.repository.enums.ERole;
import com.hrm.repository.enums.EStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class CompanyManager extends BaseEntity{
    @Id
    private String id;
    private String job;
    private Long authId;
    private Long companyId;
    private String image;
    private String name;
    private String secondName;
    private String lastName;
    private String secondLastName;
    private LocalDate birthDate;
    private String placeOfBirth;
    private Long identificationNumber;
    private String email;
    private String address;
    private String phoneNumber;
    @Builder.Default
    private EStatus status = EStatus.ACTIVE;
    private LocalDateTime startDayOfWork;
    @Builder.Default
    private EDepartment department = EDepartment.DEFAULT;
}

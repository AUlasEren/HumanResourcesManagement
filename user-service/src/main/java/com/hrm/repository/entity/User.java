package com.hrm.repository.entity;

import com.hrm.repository.enums.ERole;
import com.hrm.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class User extends BaseEntity{
    @Id
    private  String id;
    private  Long authId;
    private  String image;
    private  String name;
    private  String secondName;
    private  String lastName;
    private  String secondLastName;
    private  LocalDate birthDate;
    private  String placeOfBirth;
    private Long identificationNumber;
    private String email;
    private String address;
    private String phoneNumber;
    private EStatus status;
    //auth DAN KAYIT OLDKTAN SONRA BU BILGILERIN YAZILACAGI SAYFAYA YONLENDIR.
}

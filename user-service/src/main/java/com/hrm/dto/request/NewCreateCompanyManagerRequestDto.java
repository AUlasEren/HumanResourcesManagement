package com.hrm.dto.request;

import com.hrm.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewCreateCompanyManagerRequestDto {
    private  String image;
//    @Pattern(regexp = "^[a-zA-Z]+$", message = "Ad soyad sadece harf içermelidir.")
//    @Size(min = 3, max = 50, message = "Ad soyad en az 3, en fazla 50 karakter olmalıdır.")
    private  String name;
//    @Pattern(regexp = "^[a-zA-Z]+$", message = "Ad soyad sadece harf içermelidir.")
//    @Size(min = 3, max = 50, message = "Ad soyad en az 3, en fazla 50 karakter olmalıdır.")
    private  String secondName;
//    @Pattern(regexp = "^[a-zA-Z]+$", message = "Ad soyad sadece harf içermelidir.")
//    @Size(min = 3, max = 50, message = "Ad soyad en az 3, en fazla 50 karakter olmalıdır.")
    private  String lastName;
//    @Pattern(regexp = "^[a-zA-Z]+$", message = "Ad soyad sadece harf içermelidir.")
//    @Size(min = 3, max = 50, message = "Ad soyad en az 3, en fazla 50 karakter olmalıdır.")
    private  String secondLastName;
    private LocalDate birthDate;
    private  String placeOfBirth;
//    @Valid
//    @Pattern(regexp = "^[1-9]{1}[0-9]{9}[0,2,4,6,8]{1}$", message = "Identification Number is invalid.")//11 hanelı olarak kabul edıcek rakamlardan olusucak , ilk hane 0 olamaz
    private Long identificationNumber;
//    @Email
    private String email;
    private String address;
//    @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$", message = "Phone Number is invalid.")
    private String phoneNumber;
    private EStatus status;
    private Long companyId;
    private String job;
}

package com.hrm.controller;

import static com.hrm.constant.ApiUrls.*;

import com.hrm.dto.response.CompanyDetailResponseDto;
import com.hrm.dto.response.CreateResponseDto;
import com.hrm.dto.response.UpdateResponseDto;
import com.hrm.repository.entity.Company;
import com.hrm.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(COMPANY)
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createCompany(@RequestBody CreateResponseDto dto) {
        return ResponseEntity.ok(companyService.createCompany(dto));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<Company> updateCompany(@RequestBody UpdateResponseDto dto){
        return ResponseEntity.ok(companyService.updateCompany(dto));
    }

    @DeleteMapping(DELETEBYID)
    public ResponseEntity<Boolean> deleteCompany(@RequestParam Long id){
        return ResponseEntity.ok(companyService.deleteCompany(id));
    }

    @GetMapping(FINDALL)
    public ResponseEntity<List<Company>> findAll() {
        return ResponseEntity.ok(companyService.findAll());
    }

    @GetMapping(DETAILINFO)
    public ResponseEntity<Company> detailInfo(@RequestParam Long id) {
        return ResponseEntity.ok(companyService.getDetailInfo(id));
    }


}

package com.hrm.controller;

import static com.hrm.constant.ApiUrls.*;

import com.hrm.dto.response.CreateResponseDto;
import com.hrm.dto.response.UpdateResponseDto;
import com.hrm.repository.entity.Company;
import com.hrm.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(COMPANY)
public class CompanyController {
    private final CompanyService companyService;
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createCompany(@RequestBody @Valid CreateResponseDto dto) {
        return ResponseEntity.ok(companyService.createCompany(dto));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(UPDATE)
    public ResponseEntity<Company> updateCompany(@RequestBody @Valid UpdateResponseDto dto){
        return ResponseEntity.ok(companyService.updateCompany(dto));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(DELETEBYID)
    public ResponseEntity<Boolean> deleteCompany(@RequestParam Long id){
        return ResponseEntity.ok(companyService.deleteCompany(id));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(FINDALL)
    public ResponseEntity<List<Company>> findAll() {
        return ResponseEntity.ok(companyService.findAll());
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(DETAILINFO)
    public ResponseEntity<Company> detailInfo(@RequestParam Long id) {
        return ResponseEntity.ok(companyService.getDetailInfo(id));
    }


}

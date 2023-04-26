package com.hrm.controller;

import com.hrm.dto.request.NewCreateCompanyManagerRequestDto;
import com.hrm.dto.request.UpdateCompanyManagerRequestDto;
import com.hrm.repository.entity.CompanyManager;
import com.hrm.service.CompanyManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hrm.constants.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(COMPANYMANAGER)
public class CompanyManagerController {
    private final CompanyManagerService companyManagerService;


    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createCompanyManager(@RequestBody NewCreateCompanyManagerRequestDto dto) {
        return ResponseEntity.ok(companyManagerService.createCompanyManager(dto));
    }

    @GetMapping(FINDALL)
    public ResponseEntity<List<CompanyManager>> findAllAdmin() {
        return ResponseEntity.ok(companyManagerService.findAll());
    }

    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updateCompanyManager(UpdateCompanyManagerRequestDto dto) {
        return ResponseEntity.ok(companyManagerService.updateCompanyManager(dto));
    }

    @DeleteMapping(DELETEBYID)
    public ResponseEntity<Boolean> deleteCompanyManager(String id) {
        return ResponseEntity.ok(companyManagerService.delete(id));
    }
}

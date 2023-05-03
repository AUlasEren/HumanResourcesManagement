package com.hrm.controller;

import com.hrm.dto.request.NewCreateCompanyManagerRequestDto;
import com.hrm.dto.request.UpdateCompanyManagerRequestDto;
import com.hrm.dto.response.CompanyManagerDetailResponseDto;
import com.hrm.repository.entity.CompanyManager;
import com.hrm.service.CompanyManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.hrm.constants.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(COMPANYMANAGER)
public class CompanyManagerController {
    private final CompanyManagerService companyManagerService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createCompanyManager(@RequestBody @Valid NewCreateCompanyManagerRequestDto dto) {
        return ResponseEntity.ok(companyManagerService.createCompanyManager(dto));
    }

    @GetMapping(FINDALL)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CompanyManager>> findAllAdmin() {
        return ResponseEntity.ok(companyManagerService.findAll());
    }

    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updateCompanyManager(@RequestBody @Valid UpdateCompanyManagerRequestDto dto) {
        return ResponseEntity.ok(companyManagerService.updateCompanyManager(dto));
    }

    @DeleteMapping(DELETEBYID)
    public ResponseEntity<Boolean> deleteCompanyManager(@RequestParam String id) {
        return ResponseEntity.ok(companyManagerService.delete(id));
    }

    @GetMapping(FINDBYID)
    public ResponseEntity<Optional<CompanyManager>> findById(@RequestParam String id){
        return ResponseEntity.ok(companyManagerService.findById(id));
    }

    @GetMapping(FINDALLBYDETAIL)
    public ResponseEntity<List<CompanyManagerDetailResponseDto>> findAllByDetail(){
        return ResponseEntity.ok(companyManagerService.findAllByDetail());
    }
}

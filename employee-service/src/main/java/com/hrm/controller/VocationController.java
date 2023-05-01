package com.hrm.controller;

import com.hrm.dto.request.NewCreateEmployeeRequestDto;
import com.hrm.dto.request.NewCreateVocationRequestDto;
import com.hrm.dto.request.UpdateEmployeeRequestDto;
import com.hrm.repository.entity.Employee;
import com.hrm.repository.entity.Vocation;
import com.hrm.service.VocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hrm.constants.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(VOCATION)
public class VocationController {

    private final VocationService vocationService;

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createVocation(@RequestBody NewCreateVocationRequestDto dto) {
        return ResponseEntity.ok(vocationService.createVocation(dto));
    }
    @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
    @GetMapping(FINDALL)
    public ResponseEntity<List<Vocation>> findAllVocation() {
        return ResponseEntity.ok(vocationService.findAll());
    }
/*    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updateVocation(UpdateEmployeeRequestDto dto) {
        return ResponseEntity.ok(vocationService.updateVocation(dto));
    }*/
}

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
import org.springframework.transaction.annotation.Transactional;
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

    @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
    @GetMapping("/findallpending")
    public ResponseEntity<List<Vocation>> findAllPendingVocation() {
        return ResponseEntity.ok(vocationService.findAllPending());
    }

    @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
    @PutMapping(APPROVEVOCATIONREQUEST)
    public ResponseEntity<Boolean> aprroveVocationRequest(String id){
        return ResponseEntity.ok(vocationService.aprroveVocationRequest(id));
    }

    @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
    @PutMapping(REJECTVOCATIONREQUEST)
    public ResponseEntity<Boolean> rejectVocationRequest(String id){
        return ResponseEntity.ok(vocationService.rejectVocationRequest(id));
    }


}
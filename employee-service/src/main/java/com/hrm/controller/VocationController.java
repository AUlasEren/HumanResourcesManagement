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

import javax.validation.Valid;
import java.util.List;

import static com.hrm.constants.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(VOCATION)
public class VocationController {

    private final VocationService vocationService;

    @PreAuthorize("hasAnyAuthority('EMPLOYEE', 'COMPANY_MANAGER')")
    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createVocation(@RequestBody @Valid NewCreateVocationRequestDto dto) {
        return ResponseEntity.ok(vocationService.createVocation(dto));
    }

    @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
    @GetMapping(FINDALL)
    public ResponseEntity<List<Vocation>> findAllVocation() {
        return ResponseEntity.ok(vocationService.findAll());
    }

    @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
    @GetMapping(FINDALLVOCATIONPENDING)
    public ResponseEntity<List<Vocation>> findAllVocationPending() {
        return ResponseEntity.ok(vocationService.findAllVocationPending());
    }

    @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
    @PutMapping(APPROVEVOCATIONREQUEST)
    public ResponseEntity<Boolean> aprroveVocationRequest(@RequestParam String id){
        return ResponseEntity.ok(vocationService.aprroveVocationRequest(id));
    }

    @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
    @PutMapping(REJECTVOCATIONREQUEST)
    public ResponseEntity<Boolean> rejectVocationRequest(@RequestParam String id){
        return ResponseEntity.ok(vocationService.rejectVocationRequest(id));
    }
    @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
    @GetMapping(FINDALLBYSORT)
    public ResponseEntity<List<Vocation>> sortedList(){
        return ResponseEntity.ok(vocationService.sortingList());
    }


}

package com.hrm.controller;

import com.hrm.dto.request.NewCreateAdvanceRequestDto;
import com.hrm.dto.request.NewCreateExpenseRequestDto;
import com.hrm.repository.entity.Advance;
import com.hrm.repository.entity.Expense;
import com.hrm.repository.entity.Vocation;
import com.hrm.service.AdvanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hrm.constants.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ADVANCE)
public class AdvanceController {

    private final AdvanceService advanceService;

    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createAdvance(@RequestBody NewCreateAdvanceRequestDto dto) {
        return ResponseEntity.ok(advanceService.createAdvance(dto));
    }

    @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
    @GetMapping(FINDALL)
    public ResponseEntity<List<Advance>> findAllAdvance() {
        return ResponseEntity.ok(advanceService.findAll());
    }

    @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
    @GetMapping(FINDALLADVANCEPENDING)
    public ResponseEntity<List<Advance>> findAllPendingAdvance() {
        return ResponseEntity.ok(advanceService.findAllAdvancePending());
    }

    @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
    @PutMapping(APPROVEADVANCEREQUEST)
    public ResponseEntity<Boolean> aprroveAdvanceRequest(String id){
        return ResponseEntity.ok(advanceService.aprroveAdvanceRequest(id));
    }

    @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
    @PutMapping(REJECTADVANCEREQUEST)
    public ResponseEntity<Boolean> rejectAdvanceRequest(String id){
        return ResponseEntity.ok(advanceService.rejectAdvanceRequest(id));
    }

    @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
    @GetMapping(FINDALLBYSORT)
    public ResponseEntity<List<Advance>> sortedList(){
        return ResponseEntity.ok(advanceService.sortingList());
    }







}

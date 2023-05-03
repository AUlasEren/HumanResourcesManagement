package com.hrm.controller;

import com.hrm.dto.request.NewCreateEmployeeRequestDto;
import com.hrm.dto.request.NewCreateExpenseRequestDto;
import com.hrm.repository.entity.Advance;
import com.hrm.repository.entity.Expense;
import com.hrm.repository.entity.Vocation;
import com.hrm.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.hrm.constants.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(EXPENSE)
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping(CREATE)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'COMPANY_MANAGER')")
    public ResponseEntity<Boolean> createExpense(@RequestBody @Valid NewCreateExpenseRequestDto dto) {
        return ResponseEntity.ok(expenseService.createExpense(dto));
    }

    @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
    @GetMapping(FINDALL)
    public ResponseEntity<List<Expense>> findAllExpense() {
        return ResponseEntity.ok(expenseService.findAll());
    }

    @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
    @GetMapping(FINDALLEXPENSEPENDING)
    public ResponseEntity<List<Expense>> findAllPendingExpense() {
        return ResponseEntity.ok(expenseService.findAllExpensePending());
    }

    @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
    @PutMapping(APPROVEEXPENSEREQUEST)
    public ResponseEntity<Boolean> aprroveExpenseRequest(@RequestParam String id){
        return ResponseEntity.ok(expenseService.aprroveExpenseRequest(id));
    }

    @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
    @PutMapping(REJECTEXPENSEREQUEST)
    public ResponseEntity<Boolean> rejectExpenseRequest(@RequestParam String id){
        return ResponseEntity.ok(expenseService.rejectExpenseRequest(id));
    }

    @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
    @GetMapping(FINDALLBYSORT)
    public ResponseEntity<List<Expense>> sortedList(){
        return ResponseEntity.ok(expenseService.sortingList());
    }



}

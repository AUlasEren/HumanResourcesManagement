package com.hrm.controller;

import com.hrm.dto.request.NewCreateEmployeeRequestDto;
import com.hrm.dto.request.UpdateEmployeeRequestDto;
import com.hrm.dto.response.EmployeeDetailResponseDto;
import com.hrm.repository.entity.Employee;
import com.hrm.repository.entity.Vocation;
import com.hrm.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.hrm.constants.ApiUrls.*;
import static com.hrm.constants.ApiUrls.FINDALLBYDETAIL;

@RestController
@RequiredArgsConstructor
@RequestMapping(EMPLOYEE)
public class EmployeeController {
    private final EmployeeService employeeService;

    @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createEmployee(@RequestBody NewCreateEmployeeRequestDto dto) {
        return ResponseEntity.ok(employeeService.createEmployee(dto));
    }

    @GetMapping(FINDALL)
    @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
    public ResponseEntity<List<Employee>> findAllEmployee() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @PutMapping(UPDATE)
    @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
    public ResponseEntity<Boolean> updateEmployee(UpdateEmployeeRequestDto dto) {
        return ResponseEntity.ok(employeeService.updateEmployee(dto));
    }

    @DeleteMapping(DELETEBYID)
    @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
    public ResponseEntity<Boolean> deleteEmployee(String id) {
        return ResponseEntity.ok(employeeService.delete(id));
    }

    @GetMapping(FINDBYID)
    @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
    public ResponseEntity<Optional<Employee>> findById(String id){
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @GetMapping(FINDALLBYDETAIL)
    @PreAuthorize("hasAuthority('COMPANY_MANAGER')")
    public ResponseEntity<List<EmployeeDetailResponseDto>> findAllByDetail(){
        return ResponseEntity.ok(employeeService.findAllByDetail());
    }

    @GetMapping(FINDALLVOCATIONBYEMPLOYEEID)
    @PreAuthorize("hasAuthority('EMPLOYEE') and hasAuthority('COMPANY_MANAGER')")
    public ResponseEntity<List<Vocation>> findAllVocationBtEmployeeId(String id){
        return ResponseEntity.ok(employeeService.findAllVocationBtEmployeeId(id));
    }


}

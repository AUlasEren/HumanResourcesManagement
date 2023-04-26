package com.hrm.controller;

import com.hrm.dto.request.NewCreateEmployeeRequestDto;
import com.hrm.dto.request.UpdateEmployeeRequestDto;
import com.hrm.repository.entity.Employee;
import com.hrm.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hrm.constants.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(EMPLOYEE)
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createEmployee(@RequestBody NewCreateEmployeeRequestDto dto) {
        return ResponseEntity.ok(employeeService.createEmployee(dto));
    }

    @GetMapping(FINDALL)
    public ResponseEntity<List<Employee>> findAllEmployee() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updateEmployee(UpdateEmployeeRequestDto dto) {
        return ResponseEntity.ok(employeeService.updateEmployee(dto));
    }

    @DeleteMapping(DELETEBYID)
    public ResponseEntity<Boolean> deleteEmployee(String id) {
        return ResponseEntity.ok(employeeService.delete(id));
    }
}

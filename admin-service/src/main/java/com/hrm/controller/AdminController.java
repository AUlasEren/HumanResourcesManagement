package com.hrm.controller;

import com.hrm.dto.request.NewCreateAdminRequestDto;
import com.hrm.dto.request.UpdateAdminRequestDto;
import com.hrm.repository.entity.Admin;
import com.hrm.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hrm.constants.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ADMIN)
public class AdminController {
    private final AdminService adminService;

    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createAdmin(@RequestBody NewCreateAdminRequestDto dto) {
        return ResponseEntity.ok(adminService.createAdmin(dto));
    }

    @GetMapping(FINDALL)
    public ResponseEntity<List<Admin>> findAllAdmin() {
        return ResponseEntity.ok(adminService.findAll());
    }

    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updateAdmin(UpdateAdminRequestDto dto) {
        return ResponseEntity.ok(adminService.updateAdmin(dto));
    }

    @DeleteMapping(DELETEBYID)
    public ResponseEntity<Boolean> deleteAdmin(String id) {
        return ResponseEntity.ok(adminService.delete(id));
    }
}

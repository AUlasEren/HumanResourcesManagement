package com.hrm.controller;

import static com.hrm.constants.ApiUrls.*;

import com.hrm.dto.request.NewCreateCompanyManagerRequestDto;
import com.hrm.dto.request.UserUpdateRequestDto;
import com.hrm.dto.response.UserDetailResponseDto;
import com.hrm.dto.response.UserSummaryResponseDto;
import com.hrm.repository.entity.Admin;
import com.hrm.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
;import java.util.List;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createUser(@RequestBody NewCreateCompanyManagerRequestDto dto){
        return ResponseEntity.ok(adminService.createUser(dto));
    }
    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updateUser(@RequestBody UserUpdateRequestDto dto){
        return ResponseEntity.ok(adminService.update(dto));
    }
    @GetMapping(FINDALL)
    public ResponseEntity<List<Admin>> findAll(){
        return ResponseEntity.ok(adminService.findAll());
    }
    @DeleteMapping(DELETEBYID)
    public ResponseEntity<Boolean> deleteUser(String id){
        return ResponseEntity.ok(adminService.delete(id));
    }

    @GetMapping(SUMMARYINFO)
    public ResponseEntity<UserSummaryResponseDto> summaryInfo(@RequestParam String id){
        return ResponseEntity.ok(adminService.getSummaryInfo(id));
    }
    @GetMapping(DETAILINFO)
    public ResponseEntity<UserDetailResponseDto> detailInfo(@RequestParam String id){
        return ResponseEntity.ok(adminService.getDetailInfo(id));
    }

    @GetMapping(FINDBYCOMPANYMANAGER)
    public ResponseEntity<List<Admin>> findByCompanyManager (){
        return ResponseEntity.ok(adminService.findByCompanyManager());
    }
    @PutMapping(DEFAULTTOMANAGERCONVERT)
    public ResponseEntity<Boolean> makeTheDefaultValueaCompanyAdministrator(String id){
        return ResponseEntity.ok(adminService.makeTheDefaultValueaCompanyAdministrator(id));
    }
}

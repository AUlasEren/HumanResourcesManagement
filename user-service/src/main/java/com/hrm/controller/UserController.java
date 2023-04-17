package com.hrm.controller;

import static com.hrm.constants.ApiUrls.*;

import com.hrm.dto.request.NewCreateUserRequestDto;
import com.hrm.dto.request.UserUpdateRequestDto;
import com.hrm.repository.entity.User;
import com.hrm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
;import java.util.List;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createUser(@RequestBody NewCreateUserRequestDto dto){
        return ResponseEntity.ok(userService.createUser(dto));
    }
    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updateUser(@RequestBody UserUpdateRequestDto dto){
        return ResponseEntity.ok(userService.update(dto));
    }
    @GetMapping(FINDALL)
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }
    @DeleteMapping(DELETEBYID)
    public ResponseEntity<Boolean> deleteUser(String id){
        return ResponseEntity.ok(userService.delete(id));
    }






}

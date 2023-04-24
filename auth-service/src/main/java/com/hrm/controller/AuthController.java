package com.hrm.controller;


import com.hrm.dto.request.NewRegisterRequestDto;
import com.hrm.dto.request.UserLoginDto;
import com.hrm.dto.response.RegisterResponseDto;
import com.hrm.exception.AuthServiceException;
import com.hrm.exception.ErrorType;
import com.hrm.repository.entity.Auth;
import com.hrm.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.hrm.constants.ApiUrls.*;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid NewRegisterRequestDto dto){
        return ResponseEntity.ok(authService.register(dto));
    }
    @GetMapping(LOGIN)
    public ResponseEntity<Boolean> login(UserLoginDto dto){
        return ResponseEntity.ok(authService.login(dto));
    }






}

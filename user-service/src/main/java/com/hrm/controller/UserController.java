package com.hrm.controller;

import static com.hrm.constants.ApiUrls.*;

import com.hrm.dto.request.NewCreateUserRequestDto;
import com.hrm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createUser(@RequestBody NewCreateUserRequestDto dto){
        return ResponseEntity.ok(userService.createUser(dto));
    }
}

package com.hrm.service;

import com.hrm.dto.request.NewRegisterRequestDto;
import com.hrm.dto.response.RegisterResponseDto;
import com.hrm.exception.AuthServiceException;
import com.hrm.exception.ErrorType;
import com.hrm.mapper.IAuthMapper;
import com.hrm.repository.IAuthRepository;
import com.hrm.repository.entity.Auth;
import com.hrm.utility.CodeGenerator;
import com.hrm.utility.ServiceManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth, Long> {

    private final IAuthRepository authRepository;


    public AuthService(IAuthRepository authRepository) {
        super(authRepository);
        this.authRepository = authRepository;


    }

    public RegisterResponseDto register(NewRegisterRequestDto dto) {
        if (authRepository.findOptionalByEmail(dto.getEmail()).isPresent())
            throw new AuthServiceException(ErrorType.EMAIL_DUPLICATE);
        if (!dto.getPassword().equals(dto.getRePassword()))
            throw new AuthServiceException(ErrorType.PASSWORD_UNMATCH);
        Auth auth = IAuthMapper.INSTANCE.toAuth(dto);
        authRepository.save(auth);
        return IAuthMapper.INSTANCE.toRegisterResponseDto(auth);
    }
}

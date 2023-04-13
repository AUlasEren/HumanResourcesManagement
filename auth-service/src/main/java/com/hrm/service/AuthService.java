package com.hrm.service;

import com.hrm.dto.request.NewRegisterRequestDto;
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

@Service
public class AuthService extends ServiceManager<Auth, Long> {

    private final IAuthRepository authRepository;


    public AuthService(IAuthRepository authRepository) {
        super(authRepository);
        this.authRepository = authRepository;


    }

    public Boolean register(NewRegisterRequestDto dto) {
        Auth auth = IAuthMapper.INSTANCE.toAuth(dto);
        auth.setActivationCode(CodeGenerator.generateCode());
        if(!dto.getPassword().equals(dto.getRePassword())){
            throw new AuthServiceException(ErrorType.PASSWORD_UNMATCH);
        }
        if (authRepository.findOptionalByEmail(dto.getEmail()).get()) {
            throw new AuthServiceException(ErrorType.EMAIL_DUPLICATE);
        }
        save(auth);
        return true;
    }
}

package com.hrm.service;

import com.hrm.dto.request.NewRegisterRequestDto;
import com.hrm.dto.response.RegisterResponseDto;
import com.hrm.exception.AuthServiceException;
import com.hrm.exception.ErrorType;
import com.hrm.mapper.IAuthMapper;
import com.hrm.rabbitmq.producer.RegisterProducer;
import com.hrm.repository.IAuthRepository;
import com.hrm.repository.entity.Auth;
import com.hrm.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class AuthService extends ServiceManager<Auth, Long> {

    private final IAuthRepository authRepository;
    private final RegisterProducer registerProducer;


    public AuthService(IAuthRepository authRepository, RegisterProducer registerProducer) {
        super(authRepository);
        this.authRepository = authRepository;
        this.registerProducer = registerProducer;
    }

    public RegisterResponseDto register(NewRegisterRequestDto dto) {
        if (authRepository.findOptionalByEmail(dto.getEmail()).isPresent())
            throw new AuthServiceException(ErrorType.EMAIL_DUPLICATE);
        if (!dto.getPassword().equals(dto.getRePassword()))
            throw new AuthServiceException(ErrorType.PASSWORD_UNMATCH);
        Auth auth = IAuthMapper.INSTANCE.toAuth(dto);
        authRepository.save(auth);
        registerProducer.sendNewUser(IAuthMapper.INSTANCE.toRegisterModel(auth));
        return IAuthMapper.INSTANCE.toRegisterResponseDto(auth);
    }
}

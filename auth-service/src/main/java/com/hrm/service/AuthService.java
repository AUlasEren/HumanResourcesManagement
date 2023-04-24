package com.hrm.service;

import com.hrm.dto.request.NewRegisterRequestDto;
import com.hrm.dto.request.UserLoginDto;
import com.hrm.dto.response.RegisterResponseDto;
import com.hrm.exception.AuthServiceException;
import com.hrm.exception.ErrorType;
import com.hrm.mapper.IAuthMapper;
import com.hrm.rabbitmq.producer.RegisterMailProducer;
import com.hrm.rabbitmq.producer.RegisterProducer;
import com.hrm.repository.IAuthRepository;
import com.hrm.repository.entity.Auth;
import com.hrm.utility.CodeGenerator;
import com.hrm.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth, Long> {

    private final IAuthRepository authRepository;
    private final RegisterProducer registerProducer;
    private final RegisterMailProducer mailProducer;


    public AuthService(IAuthRepository authRepository, RegisterProducer registerProducer, RegisterMailProducer mailProducer) {
        super(authRepository);
        this.authRepository = authRepository;
        this.registerProducer = registerProducer;
        this.mailProducer = mailProducer;
    }

    public RegisterResponseDto register(NewRegisterRequestDto dto) {
        if (authRepository.findOptionalByEmail(dto.getEmail()).isPresent())
            throw new AuthServiceException(ErrorType.EMAIL_DUPLICATE);
        Auth auth = IAuthMapper.INSTANCE.toAuth(dto);
        String code = CodeGenerator.generateCode();
        auth.setActivationCode(code);
        auth.setPassword(code);
        authRepository.save(auth);
        registerProducer.sendNewUser(IAuthMapper.INSTANCE.toRegisterModel(auth));
        mailProducer.sendNewMail(IAuthMapper.INSTANCE.toRegisterMailModel(auth));
        return IAuthMapper.INSTANCE.toRegisterResponseDto(auth);
    }

    public Boolean login(UserLoginDto dto) {
        if (authRepository.findOptionalByEmail(dto.getEmail()).isEmpty())
            throw new AuthServiceException(ErrorType.LOGIN_ERROR);
        Optional<Auth> auth = authRepository.findOptionalByEmail(dto.getEmail());
        if (!auth.get().getPassword().equals(dto.getPassword()))
            throw new AuthServiceException(ErrorType.LOGIN_ERROR);
        return true;

    }
}

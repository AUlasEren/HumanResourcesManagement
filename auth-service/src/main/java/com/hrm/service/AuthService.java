package com.hrm.service;

import com.hrm.dto.request.NewRegisterRequestDto;
import com.hrm.dto.request.UserLoginDto;
import com.hrm.dto.response.RegisterResponseDto;
import com.hrm.exception.AuthServiceException;
import com.hrm.exception.ErrorType;
import com.hrm.mapper.IAuthMapper;
import com.hrm.rabbitmq.model.MailModel;
import com.hrm.rabbitmq.model.RegisterAdminModel;
import com.hrm.rabbitmq.producer.MailProducer;
import com.hrm.repository.IAuthRepository;
import com.hrm.repository.entity.Auth;
import com.hrm.utility.CodeGenerator;
import com.hrm.utility.ServiceManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth, Long> {
    private final MailProducer mailProducer;
    private final IAuthRepository authRepository;
    public AuthService(MailProducer mailProducer, IAuthRepository authRepository) {
        super(authRepository);
        this.mailProducer = mailProducer;
        this.authRepository = authRepository;
    }
    @Transactional
    public RegisterResponseDto register(NewRegisterRequestDto dto) {
        if (authRepository.findOptionalByEmail(dto.getEmail()).isPresent())
            throw new AuthServiceException(ErrorType.EMAIL_DUPLICATE);
        Auth auth = IAuthMapper.INSTANCE.toAuth(dto);
        String code = CodeGenerator.generateCode();
        auth.setActivationCode(code);
        auth.setPassword(code);
        authRepository.save(auth);
        mailProducer.sendNewMail(MailModel.builder().activationCode(auth.getActivationCode()).email(auth.getEmail()).build());
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

    public Boolean createAdminWithRabbitMq(RegisterAdminModel model) {
        if (authRepository.findOptionalByEmail(model.getEmail()).isPresent())
            throw new AuthServiceException(ErrorType.EMAIL_DUPLICATE);
        //Auth auth = IAuthMapper.INSTANCE.toAuth(model);
        Auth auth = Auth.builder().email(model.getEmail()).build();
        String code = CodeGenerator.generateCode();
        auth.setActivationCode(code);
        auth.setPassword(code);
        authRepository.save(auth);
        mailProducer.sendNewMail(MailModel.builder().activationCode(auth.getActivationCode()).email(auth.getEmail()).build());
        return true;
    }
}



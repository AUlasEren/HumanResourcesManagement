package com.hrm.service;

import com.hrm.dto.request.NewRegisterRequestDto;
import com.hrm.dto.request.UserLoginDto;
import com.hrm.dto.response.RegisterResponseDto;
import com.hrm.exception.AuthServiceException;
import com.hrm.exception.ErrorType;
import com.hrm.mapper.IAuthMapper;
import com.hrm.rabbitmq.model.MailModel;
import com.hrm.rabbitmq.model.RegisterAdminModel;
import com.hrm.rabbitmq.model.RegisterCompanyManagerModel;
import com.hrm.rabbitmq.model.RegisterEmployeeModel;
import com.hrm.rabbitmq.producer.MailProducer;
import com.hrm.repository.IAuthRepository;
import com.hrm.repository.entity.Auth;
import com.hrm.utility.CodeGenerator;
import com.hrm.utility.JwtTokenManager;
import com.hrm.utility.ServiceManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth, Long> {
    private final MailProducer mailProducer;
    private final IAuthRepository authRepository;
    private final JwtTokenManager jwtTokenManager;

    public AuthService(MailProducer mailProducer, IAuthRepository authRepository, JwtTokenManager jwtTokenManager) {
        super(authRepository);
        this.mailProducer = mailProducer;
        this.authRepository = authRepository;
        this.jwtTokenManager = jwtTokenManager;
    }

    @Transactional
    public RegisterResponseDto register(NewRegisterRequestDto dto) {
        if (authRepository.findOptionalByEmail(dto.getEmail()).isPresent())
            throw new AuthServiceException(ErrorType.EMAIL_DUPLICATE);
        Auth auth = IAuthMapper.INSTANCE.toAuth(dto);
        String code = CodeGenerator.generateCode();
        auth.setActivationCode(code);
        auth.setPassword(code);
        String role = "ADMIN";
        auth.setRole(role);
        save(auth);
        mailProducer.sendNewMail(MailModel.builder().activationCode(auth.getActivationCode()).email(auth.getEmail()).build());
        return IAuthMapper.INSTANCE.toRegisterResponseDto(auth);
    }

    public String login(UserLoginDto dto) {
        if (authRepository.findOptionalByEmail(dto.getEmail()).isEmpty())
            throw new AuthServiceException(ErrorType.LOGIN_ERROR);
        Optional<Auth> auth = authRepository.findOptionalByEmail(dto.getEmail());
        if (!auth.get().getPassword().equals(dto.getPassword()))
            throw new AuthServiceException(ErrorType.LOGIN_ERROR);
        return jwtTokenManager.createToken(auth.get()).orElseThrow(() -> {
            throw new AuthServiceException(ErrorType.TOKEN_NOT_CREATED);
        });
    }

    public Boolean createAdminWithRabbitMq(RegisterAdminModel model) {
        if (authRepository.findOptionalByEmail(model.getEmail()).isPresent())
            throw new AuthServiceException(ErrorType.EMAIL_DUPLICATE);
        //Auth auth = IAuthMapper.INSTANCE.toAuth(model);
        Auth auth = Auth.builder().email(model.getEmail()).build();
        String code = CodeGenerator.generateCode();
        String role = "ADMIN";
        auth.setActivationCode(code);
        auth.setPassword(code);
        auth.setRole(role);
        save(auth);
        mailProducer.sendNewMail(MailModel.builder().activationCode(auth.getActivationCode()).email(auth.getEmail()).build());
        return true;
    }

    public Boolean createCompanyManagerWithRabbitMq(RegisterCompanyManagerModel model) {
        if (authRepository.findOptionalByEmail(model.getEmail()).isPresent())
            throw new AuthServiceException(ErrorType.EMAIL_DUPLICATE);
        //Auth auth = IAuthMapper.INSTANCE.toAuth(model);
        Auth auth = Auth.builder().email(model.getEmail()).build();
        String code = CodeGenerator.generateCode();
        String role = "COMPANY_MANAGER";
        auth.setActivationCode(code);
        auth.setPassword(code);
        auth.setRole(role);
        save(auth);
        mailProducer.sendNewMail(MailModel.builder().activationCode(auth.getActivationCode()).email(auth.getEmail()).build());
        return true;
    }

    public Boolean createEmployeeWithRabbitMq(RegisterEmployeeModel model) {
        if (authRepository.findOptionalByEmail(model.getEmail()).isPresent())
            throw new AuthServiceException(ErrorType.EMAIL_DUPLICATE);
        //Auth auth = IAuthMapper.INSTANCE.toAuth(model);
        Auth auth = Auth.builder().email(model.getEmail()).build();
        String code = CodeGenerator.generateCode();
        String role = "EMPLOYEE";
        auth.setActivationCode(code);
        auth.setPassword(code);
        auth.setRole(role);
        save(auth);
        mailProducer.sendNewMail(MailModel.builder().activationCode(auth.getActivationCode()).email(auth.getEmail()).build());
        return true;
    }
}



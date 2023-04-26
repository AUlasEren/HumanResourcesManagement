package com.hrm.service;


import com.hrm.dto.request.NewCreateCompanyManagerRequestDto;
import com.hrm.dto.request.UserUpdateRequestDto;
import com.hrm.dto.response.UserDetailResponseDto;
import com.hrm.dto.response.UserSummaryResponseDto;
import com.hrm.exception.ErrorType;
import com.hrm.exception.UserServiceException;
import com.hrm.mapper.IAdminMapper;
import com.hrm.rabbitmq.model.RegisterModel;
import com.hrm.rabbitmq.model.UserMailModel;
import com.hrm.rabbitmq.producer.CreateUserProducer;
import com.hrm.repository.IAdminRepository;
import com.hrm.repository.entity.Admin;
import com.hrm.repository.enums.ERole;
import com.hrm.repository.enums.EStatus;
import com.hrm.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService extends ServiceManager<Admin, String> {

    private final IAdminRepository adminRepository;
    private final CreateUserProducer createUserProducer;


    public AdminService(IAdminRepository userRepository, CreateUserProducer createUserProducer) {
        super(userRepository);
        this.adminRepository = userRepository;
        this.createUserProducer = createUserProducer;
    }


    public Boolean createUser(NewCreateCompanyManagerRequestDto dto) {
        if (adminRepository.findOptionalByIdentificationNumber(dto.getIdentificationNumber())
                .isPresent())
            throw new UserServiceException(ErrorType.IDENTIFICATIONNUMBER_DUPLICATE);
        save(IAdminMapper.INSTANCE.toUser(dto));
        createUserProducer.sendNewUser(UserMailModel.builder().email(dto.getEmail()).build());
        return true;
    }

    public Boolean update(UserUpdateRequestDto dto) {
        Optional<Admin> user = adminRepository.findById(dto.getId());
        if (user.isEmpty()) {
            throw new UserServiceException(ErrorType.ID_NOT_FOUND);
        }
        user.get().setImage(dto.getImage());
        user.get().setAddress(dto.getAddress());
        user.get().setPhoneNumber(dto.getPhoneNumber());
        update(user.get());
        return true;
    }

    public Boolean delete(String id) {
        Optional<Admin> user = findById(id);
        if (user.isEmpty())
            throw new UserServiceException(ErrorType.ID_NOT_FOUND);
        user.get().setStatus(EStatus.DELETED);
        update(user.get());
        return true;
    }

    public Boolean createUserWithRabbitMq(RegisterModel model) {
        try {
            Admin admin = save(IAdminMapper.INSTANCE.toUser(model));
            return true;
        } catch (Exception e) {
            throw new UserServiceException(ErrorType.USER_NOT_CREATED);
        }
    }

    public UserSummaryResponseDto getSummaryInfo(String id) {
        Optional<Admin> user = adminRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserServiceException(ErrorType.ID_NOT_FOUND);
        }
        return IAdminMapper.INSTANCE.toUserSummaryResponseDto(user.get());
    }

    public UserDetailResponseDto getDetailInfo(String id) {
        Optional<Admin> user = adminRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserServiceException(ErrorType.ID_NOT_FOUND);
        }
        return IAdminMapper.INSTANCE.toUserDetailResponseDto(user.get());
    }

    public List<Admin> findByCompanyManager() {
        List<Admin> list = adminRepository.findAllByRole(ERole.COMPANY_MANAGER);
        return list;
    }
    public Boolean makeTheDefaultValueaCompanyAdministrator(String id){
        Optional<Admin> user = adminRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserServiceException(ErrorType.ID_NOT_FOUND);
        }
        user.get().setRole(ERole.COMPANY_MANAGER);
        update(user.get());
        return true;
    }
}

package com.hrm.service;


import com.hrm.dto.request.NewCreateUserRequestDto;
import com.hrm.dto.request.UserUpdateRequestDto;
import com.hrm.dto.response.UserDetailResponseDto;
import com.hrm.dto.response.UserSummaryResponseDto;
import com.hrm.exception.ErrorType;
import com.hrm.exception.UserServiceException;
import com.hrm.mapper.IUserMapper;
import com.hrm.rabbitmq.model.RegisterModel;
import com.hrm.repository.IUserRepository;
import com.hrm.repository.entity.User;
import com.hrm.repository.enums.ERole;
import com.hrm.repository.enums.EStatus;
import com.hrm.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService extends ServiceManager<User, String> {

    private final IUserRepository userRepository;


    public UserService(IUserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }


    public Boolean createUser(NewCreateUserRequestDto dto) {
        if (userRepository.findOptionalByIdentificationNumber(dto.getIdentificationNumber())
                .isPresent())
            throw new UserServiceException(ErrorType.IDENTIFICATIONNUMBER_DUPLICATE);
        save(IUserMapper.INSTANCE.toUser(dto));
        return true;
    }

    public Boolean update(UserUpdateRequestDto dto) {
        Optional<User> user = userRepository.findById(dto.getId());
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
        Optional<User> user = findById(id);
        if (user.isEmpty())
            throw new UserServiceException(ErrorType.ID_NOT_FOUND);
        user.get().setStatus(EStatus.DELETED);
        update(user.get());
        return true;
    }

    public Boolean createUserWithRabbitMq(RegisterModel model) {
        try {
            User user = save(IUserMapper.INSTANCE.toUser(model));
            return true;
        } catch (Exception e) {
            throw new UserServiceException(ErrorType.USER_NOT_CREATED);
        }
    }

    public UserSummaryResponseDto getSummaryInfo(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserServiceException(ErrorType.ID_NOT_FOUND);
        }
        return IUserMapper.INSTANCE.toUserSummaryResponseDto(user.get());
    }

    public UserDetailResponseDto getDetailInfo(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserServiceException(ErrorType.ID_NOT_FOUND);
        }
        return IUserMapper.INSTANCE.toUserDetailResponseDto(user.get());
    }

    public List<User> findByCompanyManager() {
        List<User> list = userRepository.findAllByRole(ERole.COMPANY_MANAGER);
        return list;
    }
    public Boolean makeTheDefaultValueaCompanyAdministrator(String id){
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserServiceException(ErrorType.ID_NOT_FOUND);
        }
        user.get().setRole(ERole.COMPANY_MANAGER);
        update(user.get());
        return true;
    }
}

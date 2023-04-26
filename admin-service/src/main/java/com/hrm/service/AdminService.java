package com.hrm.service;


import com.hrm.dto.request.NewCreateAdminRequestDto;
import com.hrm.dto.request.UpdateAdminRequestDto;
import com.hrm.exception.ErrorType;
import com.hrm.exception.AdminServiceException;
import com.hrm.mapper.IAdminMapper;
import com.hrm.repository.IAdminRepository;
import com.hrm.repository.entity.Admin;
import com.hrm.repository.enums.EStatus;
import com.hrm.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService extends ServiceManager<Admin, String> {

    private final IAdminRepository adminRepository;


    public AdminService(IAdminRepository adminRepository) {
        super(adminRepository);
        this.adminRepository = adminRepository;
    }

    public Boolean createAdmin(NewCreateAdminRequestDto dto) {
        if (adminRepository.findOptionalByEmail(dto.getEmail()).isPresent())
            throw new AdminServiceException(ErrorType.EMAIL_DUPLICATE);
        save(IAdminMapper.INSTANCE.toAdmin(dto));
        // bu kaydı rabbitle autha göndereceğiz.
        return true;
    }

    public Boolean updateAdmin(UpdateAdminRequestDto dto) {
        Optional<Admin> admin = adminRepository.findById(dto.getId());
        if (admin.isEmpty()) {
            throw new AdminServiceException(ErrorType.ID_NOT_FOUND);
        }
        admin.get().setImage(dto.getImage());
        admin.get().setAddress(dto.getAddress());
        admin.get().setPhoneNumber(dto.getPhoneNumber());
        update(admin.get());
        return true;
    }

    public Boolean delete(String id) {
        Optional<Admin> admin = findById(id);
        if (admin.isEmpty())
            throw new AdminServiceException(ErrorType.ID_NOT_FOUND);
        admin.get().setStatus(EStatus.DELETED);
        update(admin.get());
        return true;
    }
}

package com.hrm.service;

import com.hrm.dto.request.NewCreateCompanyManagerRequestDto;
import com.hrm.dto.request.UpdateCompanyManagerRequestDto;
import com.hrm.exception.ErrorType;
import com.hrm.exception.CompanyManagerServiceException;
import com.hrm.mapper.ICompanyManagerMapper;
import com.hrm.repository.ICompanyManagerRepository;
import com.hrm.repository.entity.CompanyManager;
import com.hrm.repository.enums.EStatus;
import com.hrm.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyManagerService extends ServiceManager<CompanyManager, String> {
    private final ICompanyManagerRepository companyManagerRepository;

    public CompanyManagerService(ICompanyManagerRepository companyManagerRepository) {
        super(companyManagerRepository);
        this.companyManagerRepository = companyManagerRepository;
    }

    public Boolean createCompanyManager(NewCreateCompanyManagerRequestDto dto) {
        if (companyManagerRepository.findOptionalByEmail(dto.getEmail()).isPresent())
            throw new CompanyManagerServiceException(ErrorType.EMAIL_DUPLICATE);
        save(ICompanyManagerMapper.INSTANCE.toCompanyManager(dto));
        // bu kaydı rabbitle autha göndereceğiz.
        return true;
    }

    public Boolean updateCompanyManager(UpdateCompanyManagerRequestDto dto) {
        Optional<CompanyManager> companyManager = companyManagerRepository.findById(dto.getId());
        if (companyManager.isEmpty()) {
            throw new CompanyManagerServiceException(ErrorType.ID_NOT_FOUND);
        }
        companyManager.get().setImage(dto.getImage());
        companyManager.get().setEmail(dto.getEmail());
        companyManager.get().setAddress(dto.getAddress());
        companyManager.get().setPhoneNumber(dto.getPhoneNumber());
        update(companyManager.get());
        return true;
    }

    public Boolean delete(String id) {
        Optional<CompanyManager> companyManager = findById(id);
        if (companyManager.isEmpty())
            throw new CompanyManagerServiceException(ErrorType.ID_NOT_FOUND);
        companyManager.get().setStatus(EStatus.DELETED);
        update(companyManager.get());
        return true;
    }
}

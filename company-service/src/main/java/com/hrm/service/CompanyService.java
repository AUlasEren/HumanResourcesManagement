package com.hrm.service;

import com.hrm.dto.response.CreateResponseDto;
import com.hrm.dto.response.UpdateResponseDto;
import com.hrm.exception.CompanyServiceException;
import com.hrm.exception.ErrorType;
import com.hrm.mapper.ICompanyMapper;
import com.hrm.repository.ICompanyRepository;
import com.hrm.repository.entity.Company;
import com.hrm.repository.enums.EStatus;
import com.hrm.utility.ServiceManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Service
public class CompanyService extends ServiceManager<Company, Long> {

    private final ICompanyRepository companyRepository;


    public CompanyService(ICompanyRepository companyRepository) {
        super(companyRepository);
        this.companyRepository = companyRepository;
    }

    public Boolean createCompany(CreateResponseDto dto) {
        Company company = ICompanyMapper.INSTANCE.toCompany(dto);
        Optional<Company> company2 = companyRepository.findOptionalByCompanyName(dto.getCompanyName());
        if (company2.isPresent()) {
            throw new CompanyServiceException(ErrorType.COMPANY_ALREADY_EXIST);
        }
        try {
            save(company);
        } catch (Exception e) {
            throw new CompanyServiceException(ErrorType.COMPANY_NOT_CREATED);
        }
        return true;
    }

    public Company getDetailInfo(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty()) {
            throw new CompanyServiceException(ErrorType.COMPANY_NOT_FOUND);
        }
        return company.get();
    }

    public Company updateCompany(UpdateResponseDto dto) {
        Optional<Company> company = companyRepository.findById(dto.getId());
        if (company.isEmpty()) {
            throw new CompanyServiceException(ErrorType.COMPANY_NOT_FOUND);
        }
        company.get().setCompanyName(dto.getCompanyName());
        company.get().setEmail(dto.getEmail());
        company.get().setTitle(dto.getTitle());
        company.get().setTaxNumber(dto.getTaxNumber());
        company.get().setTaxAdministration(dto.getTaxAdministration());
        company.get().setImage(dto.getImage());
        company.get().setPhoneNumber(dto.getPhoneNumber());
        company.get().setAddress(dto.getAddress());
        company.get().setNumberOfEmployees(dto.getNumberOfEmployees());
        company.get().setFoundationYear(dto.getFoundationYear());
        company.get().setContractStartDate(dto.getContractStartDate());
        company.get().setContractFinishDate(dto.getContractFinishDate());
        update(company.get());
        return company.get();
    }

    public Boolean deleteCompany(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty()) {
            throw new CompanyServiceException(ErrorType.COMPANY_NOT_FOUND);
        }
        company.get().setStatus(EStatus.DELETED);
        update(company.get());
        return true;
    }
}

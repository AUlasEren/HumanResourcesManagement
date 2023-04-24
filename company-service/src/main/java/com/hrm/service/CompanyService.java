package com.hrm.service;

import com.hrm.dto.response.CreateResponseDto;
import com.hrm.exception.CompanyServiceException;
import com.hrm.exception.ErrorType;
import com.hrm.mapper.ICompanyMapper;
import com.hrm.repository.ICompanyRepository;
import com.hrm.repository.entity.Company;
import com.hrm.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService extends ServiceManager<Company,Long> {

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
            companyRepository.save(company);
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
}

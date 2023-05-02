package com.hrm.service;

import com.hrm.dto.request.NewCreateVocationRequestDto;
import com.hrm.exception.EmployeeServiceException;
import com.hrm.exception.ErrorType;
import com.hrm.mapper.IVocationMapper;
import com.hrm.repository.IEmployeeRepository;
import com.hrm.repository.IVocationRepository;
import com.hrm.repository.entity.Vocation;
import com.hrm.repository.enums.EStatus;
import com.hrm.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VocationService extends ServiceManager<Vocation, String> {
    private final IVocationRepository vocationRepository;
    private final EmployeeService employeService;
    private final IEmployeeRepository employeeRepository;

    public VocationService(IVocationRepository vocationRepository, EmployeeService employeService, IEmployeeRepository employeeRepository) {
        super(vocationRepository);
        this.vocationRepository = vocationRepository;
        this.employeService = employeService;
        this.employeeRepository = employeeRepository;
    }
    public Boolean createVocation(NewCreateVocationRequestDto dto){
        if (employeeRepository.findOptionalById(dto.getEmployeeId()).isEmpty())
            throw new EmployeeServiceException(ErrorType.ID_NOT_FOUND);
        if (dto.getStartOfVocationDate() == null && dto.getEndOfVocationDate() == null)
            throw new EmployeeServiceException(ErrorType.VOCATION_NOT_CREATED);
        Vocation vocation = IVocationMapper.INSTANCE.toVocation(dto);
        long daysBetween = ChronoUnit.DAYS.between(dto.getStartOfVocationDate(),dto.getEndOfVocationDate());
        vocation.setVocationDuration(daysBetween);
        save(vocation);
        // bu kaydı rabbitle autha göndereceğiz.
      //  registerEmployeeProducer.sendNewEmployee(IEmployeeMapper.INSTANCE.toModel(employe));
        return true;
    }

    public List<Vocation> findAllVocationPending() {
        List<Vocation> findAllList= vocationRepository.findAll();
        List<Vocation> findAllVocationPendingList= new ArrayList<>();
        System.out.println("Pending list-->");
        findAllList.forEach(x->{
            if(x.getVocationStatus().equals(EStatus.PENDING))
                findAllVocationPendingList.add(x);
        });
        return findAllVocationPendingList;

    }

    public Boolean aprroveVocationRequest(String id) {
        Optional<Vocation> vocation = findById(id);
        if(vocation.isEmpty()){
            throw new EmployeeServiceException(ErrorType.VOCATION_NOT_VALID);
        }
        vocation.get().setVocationStatus(EStatus.ACCEPT);
        vocation.get().setResponseOfVocationRequestDate(LocalDate.now());
        update(vocation.get());
        return true;
    }

    public Boolean rejectVocationRequest(String id) {
        Optional<Vocation> vocation = findById(id);
        if(vocation.isEmpty()){
            throw new EmployeeServiceException(ErrorType.VOCATION_NOT_VALID);
        }
        vocation.get().setVocationStatus(EStatus.REJECT);
        vocation.get().setResponseOfVocationRequestDate(LocalDate.now());
        update(vocation.get());
        return true;
    }
}

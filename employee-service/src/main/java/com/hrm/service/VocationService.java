package com.hrm.service;

import com.hrm.dto.request.NewCreateVocationRequestDto;
import com.hrm.exception.EmployeeServiceException;
import com.hrm.exception.ErrorType;
import com.hrm.mapper.IEmployeeMapper;
import com.hrm.mapper.IVocationMapper;
import com.hrm.repository.IEmployeeRepository;
import com.hrm.repository.IVocationRepository;
import com.hrm.repository.entity.Employee;
import com.hrm.repository.entity.Vocation;
import com.hrm.repository.enums.EVocationStatus;
import com.hrm.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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
        if (employeeRepository.findOptionalById(dto.getEmployeeId()).isPresent())
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

    public List<Vocation> findAllPending() {
        List<Vocation> findAllList= vocationRepository.findAll();
        List<Vocation> findAllPendingList= new ArrayList<>();
        System.out.println("Pending list-->");
        findAllList.forEach(x->{
            if(x.getVocationStatus().equals(EVocationStatus.PENDING))
                findAllPendingList.add(x);
        });

        return findAllPendingList;

    }
}

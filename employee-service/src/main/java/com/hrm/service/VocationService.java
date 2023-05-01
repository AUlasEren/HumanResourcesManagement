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
import com.hrm.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

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
        Vocation vocation = IVocationMapper.INSTANCE.toVocation(dto);
        vocation.setVocationDuration(dayOffCalculation(dto.getStartOfVocationDate(),dto.getEndOfVocationDate()));
        save(vocation);
        // bu kaydı rabbitle autha göndereceğiz.
      //  registerEmployeeProducer.sendNewEmployee(IEmployeeMapper.INSTANCE.toModel(employe));
        return true;
    }
    public Long dayOffCalculation(LocalDate startDay , LocalDate endDay){
       return ChronoUnit.DAYS.between(startDay, endDay);
   /*     Period period = Period.between(startDay, endDay);
        long days = period.getDays();
        return days;*/
    }
}

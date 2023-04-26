package com.hrm.service;

import com.hrm.dto.request.NewCreateEmployeeRequestDto;
import com.hrm.dto.request.UpdateEmployeeRequestDto;
import com.hrm.exception.EmployeeServiceException;
import com.hrm.exception.ErrorType;
import com.hrm.mapper.IEmployeeMapper;
import com.hrm.repository.IEmployeeRepository;
import com.hrm.repository.entity.Employee;
import com.hrm.repository.enums.EStatus;
import com.hrm.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService extends ServiceManager<Employee, String> {
    private final IEmployeeRepository employeeRepository;

    public EmployeeService(IEmployeeRepository employeeRepository) {
        super(employeeRepository);
        this.employeeRepository = employeeRepository;
    }

    public Boolean createEmployee(NewCreateEmployeeRequestDto dto) {
        if (employeeRepository.findOptionalByEmail(dto.getEmail()).isPresent())
            throw new EmployeeServiceException(ErrorType.EMAIL_DUPLICATE);
        save(IEmployeeMapper.INSTANCE.toEmployee(dto));
        // bu kaydı rabbitle autha göndereceğiz.
        return true;
    }

    public Boolean updateEmployee(UpdateEmployeeRequestDto dto) {
        Optional<Employee> employee = employeeRepository.findById(dto.getId());
        if (employee.isEmpty()) {
            throw new EmployeeServiceException(ErrorType.ID_NOT_FOUND);
        }
        employee.get().setImage(dto.getImage());
        employee.get().setAddress(dto.getAddress());
        employee.get().setPhoneNumber(dto.getPhoneNumber());
        update(employee.get());
        return true;
    }

    public Boolean delete(String id) {
        Optional<Employee> employee = findById(id);
        if (employee.isEmpty())
            throw new EmployeeServiceException(ErrorType.ID_NOT_FOUND);
        employee.get().setStatus(EStatus.DELETED);
        update(employee.get());
        return true;
    }
}

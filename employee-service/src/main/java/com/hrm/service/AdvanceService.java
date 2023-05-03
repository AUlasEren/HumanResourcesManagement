package com.hrm.service;

import com.hrm.dto.request.NewCreateAdvanceRequestDto;
import com.hrm.exception.EmployeeServiceException;
import com.hrm.exception.ErrorType;
import com.hrm.mapper.IAdvanceMapper;
import com.hrm.repository.IAdvanceRepository;
import com.hrm.repository.IEmployeeRepository;
import com.hrm.repository.entity.Advance;
import com.hrm.repository.entity.Employee;
import com.hrm.repository.entity.Expense;
import com.hrm.repository.entity.Vocation;
import com.hrm.repository.enums.EStatus;
import com.hrm.utility.ServiceManager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class AdvanceService  extends ServiceManager<Advance, String> {

    private final IAdvanceRepository advanceRepository;
    private final IEmployeeRepository employeeRepository;

    public AdvanceService(IAdvanceRepository advanceRepository, IEmployeeRepository employeeRepository) {
        super(advanceRepository);
        this.advanceRepository = advanceRepository;
        this.employeeRepository = employeeRepository;
    }


    public Boolean createAdvance(NewCreateAdvanceRequestDto dto) {
        Optional<Employee> employee = employeeRepository.findById(dto.getEmployeeId());
        if (employee.isEmpty())
            throw new EmployeeServiceException(ErrorType.ID_NOT_FOUND);
        if (dto.getAdvanceAmount()> employee.get().getSalary()*3)
            throw new EmployeeServiceException(ErrorType.AMOUNT_NOT_VALIABLE);
        if (dto.getAdvanceType() == null || dto.getAdvanceAmount() == 0)
            throw new EmployeeServiceException(ErrorType.AMOUND_NOT_NULL);
        Advance advance = IAdvanceMapper.INSTANCE.toAdvance(dto);
        save(advance);
        return true;
    }

    public List<Advance> findAllAdvancePending() {
        List<Advance> findAllAdvanceList= advanceRepository.findAll();
        List<Advance> findAllAdvancePendingList= new ArrayList<>();
        findAllAdvanceList.forEach(x->{
            if(x.getAdvanceStatus().equals(EStatus.PENDING))
                findAllAdvancePendingList.add(x);
        });
        return findAllAdvancePendingList;
    }

    public Boolean aprroveAdvanceRequest(String id) {
        Optional<Advance> advance = findById(id);
        if(advance.isEmpty()){
            throw new EmployeeServiceException(ErrorType.ADVANCE_NOT_VALID);
        }
        advance.get().setAdvanceStatus(EStatus.ACCEPT);
        advance.get().setResponseDateOfAdvanceRequest(LocalDate.now());
        update(advance.get());
        return true;
    }

    public Boolean rejectAdvanceRequest(String id) {
        Optional<Advance> advance = findById(id);
        if(advance.isEmpty()){
            throw new EmployeeServiceException(ErrorType.ADVANCE_NOT_VALID);
        }
        advance.get().setAdvanceStatus(EStatus.REJECT);
        advance.get().setResponseDateOfAdvanceRequest(LocalDate.now());
        update(advance.get());
        return true;
    }

    public List<Advance> sortingList(){
        List<Advance> advanceList = findAll();
        List<Advance> sortedList = sortByFifo(advanceList);
        return sortedList;
    }

    public List<Advance> sortByFifo(List<Advance> advanceList){
        advanceList.sort(Comparator.comparing(Advance::getCreateDate));
        advanceList.sort(Comparator.comparing(Advance::getCreateDate));
        List<Advance> sortedList = new ArrayList<>();
        for (Advance advance : advanceList) {
            if (advance.getAdvanceStatus() == EStatus.PENDING) {
                sortedList.add(advance);
            }
        }
        for (Advance advance : advanceList) {
            if (advance.getAdvanceStatus() == EStatus.ACCEPT) {
                sortedList.add(advance);
            }
        }
        for (Advance advance : advanceList) {
            if (advance.getAdvanceStatus() == EStatus.REJECT) {
                sortedList.add(advance);
            }
        }
        return sortedList;
    }







}

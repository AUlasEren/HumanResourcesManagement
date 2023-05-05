package com.hrm.service;

import com.hrm.dto.request.NewCreateExpenseRequestDto;
import com.hrm.exception.EmployeeServiceException;
import com.hrm.exception.ErrorType;
import com.hrm.mapper.IExpenseMapper;
import com.hrm.repository.IEmployeeRepository;
import com.hrm.repository.IExpenseRepository;
import com.hrm.repository.entity.Expense;
import com.hrm.repository.enums.EStatus;
import com.hrm.utility.ServiceManager;

import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService extends ServiceManager<Expense, String> {

    private final IExpenseRepository expenseRepository;
    private final IEmployeeRepository employeeRepository;




    public ExpenseService(IExpenseRepository expenseRepository, IEmployeeRepository employeeRepository) {
        super(expenseRepository);
        this.expenseRepository = expenseRepository;
        this.employeeRepository = employeeRepository;
    }

    public Boolean createExpense(NewCreateExpenseRequestDto dto) {
        if (employeeRepository.findOptionalById(dto.getEmployeeId()).isPresent())
            throw new EmployeeServiceException(ErrorType.ID_NOT_FOUND);
        if (dto.getRequestDateOfExpense() == null || dto.getExpenseAmount() == 0)
            throw new EmployeeServiceException(ErrorType.AMOUND_NOT_NULL);
        Expense expense = IExpenseMapper.INSTANCE.toExpense(dto);
        save(expense);
        return true;
    }


    public List<Expense> findAllExpensePending() {
        List<Expense> findAllExpenseList= expenseRepository.findAll();
        List<Expense> findAllExpensePendingList= new ArrayList<>();
        findAllExpenseList.forEach(x->{
            if(x.getExpenseStatus().equals(EStatus.PENDING))
                findAllExpensePendingList.add(x);
        });
        return findAllExpensePendingList;
    }


    public Boolean aprroveExpenseRequest(String id) {
        Optional<Expense> expense = findById(id);
        if(expense.isEmpty()){
            throw new EmployeeServiceException(ErrorType.EXPENSE_NOT_VALID);
        }
        expense.get().setExpenseStatus(EStatus.ACCEPT);
        expense.get().setResponseDateOfExpenseRequest(LocalDate.now());
        update(expense.get());
        return true;
    }

    public Boolean rejectExpenseRequest(String id) {
        Optional<Expense> expense = findById(id);
        if(expense.isEmpty()){
            throw new EmployeeServiceException(ErrorType.EXPENSE_NOT_VALID);
        }
        expense.get().setExpenseStatus(EStatus.REJECT);
        expense.get().setResponseDateOfExpenseRequest(LocalDate.now());
        update(expense.get());
        return true;
    }

    public List<Expense> sortingList(){
        List<Expense> expenseList = findAll();
        List<Expense> sortedList = sortByFifo(expenseList);
        return sortedList;
    }

    public List<Expense> sortByFifo(List<Expense> expenseList){
        expenseList.sort(Comparator.comparing(Expense::getCreateDate));
        expenseList.sort(Comparator.comparing(Expense::getCreateDate));
        List<Expense> sortedList = new ArrayList<>();
        for (Expense expense : expenseList) {
            if (expense.getExpenseStatus() == EStatus.PENDING) {
                sortedList.add(expense);
            }
        }
        for (Expense expense : expenseList) {
            if (expense.getExpenseStatus() == EStatus.ACCEPT) {
                sortedList.add(expense);
            }
        }
        for (Expense expense : expenseList) {
            if (expense.getExpenseStatus() == EStatus.REJECT) {
                sortedList.add(expense);
            }
        }
        return sortedList;
    }
}

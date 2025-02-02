package org.example.rangohrmangement.services;

import org.example.rangohrmangement.dtos.PayrollDTO;
import org.example.rangohrmangement.models.Employee;
import org.example.rangohrmangement.models.Payroll;
import org.example.rangohrmangement.repositories.PayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayrollService {

    @Autowired
    private PayrollRepository payrollRepository;

    @Autowired
    private EmployeeService employeeService;

    public List<Payroll> getAllPayrolls() {
        return payrollRepository.findAll();
    }

    public Payroll getPayrollById(Long id) {
        return payrollRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payroll not found"));
    }

    public void createPayroll(PayrollDTO payrollDTO) {
        Employee employee = employeeService.getEmployeeById(payrollDTO.getEmployeeId());
        if (employee == null) {
            throw new RuntimeException("Employee not found with ID: " + payrollDTO.getEmployeeId());
        }

        Payroll payroll = new Payroll();
        payroll.setAmount(payrollDTO.getAmount());
        payroll.setPaymentDate(payrollDTO.getPaymentDate());
        payroll.setEmployee(employee);

        payrollRepository.save(payroll);
    }

    public void updatePayroll(Long id, PayrollDTO payrollDTO) {
        Payroll payroll = getPayrollById(id);
        Employee employee = employeeService.getEmployeeById(payrollDTO.getEmployeeId());
        if (employee == null) {
            throw new RuntimeException("Employee not found with ID: " + payrollDTO.getEmployeeId());
        }

        payroll.setAmount(payrollDTO.getAmount());
        payroll.setPaymentDate(payrollDTO.getPaymentDate());
        payroll.setEmployee(employee);

        payrollRepository.save(payroll);
    }

    public void deletePayroll(Long id) {
        payrollRepository.deleteById(id);
    }
}
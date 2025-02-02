package org.example.rangohrmangement.services;

import org.example.rangohrmangement.models.Employee;
import org.example.rangohrmangement.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> getEmployeesByDepartmentId(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }

    public boolean isEmailUnique(String email) {
        return employeeRepository.findByEmail(email).isEmpty();
    }

    public double calculateAverageSalary() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            return 0.0;
        }
        double totalSalary = employees.stream().mapToDouble(Employee::getSalary).sum();
        return totalSalary / employees.size();
    }

    public List<Employee> getEmployeesWithSalaryAbove(double salary) {
        return employeeRepository.findBySalaryGreaterThan(salary);
    }
}
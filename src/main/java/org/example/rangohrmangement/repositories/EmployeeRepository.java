package org.example.rangohrmangement.repositories;

import org.example.rangohrmangement.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartmentId(Long departmentId);
    Optional<Employee> findByEmail(String email);
    List<Employee> findBySalaryGreaterThan(double salary);
}
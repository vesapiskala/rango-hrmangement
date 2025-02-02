package org.example.rangohrmangement.repositories;

import org.example.rangohrmangement.models.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {
    Optional<Payroll> findById(Long id);
    List<Payroll> findByEmployeeId(Long employeeId);
}
package org.example.rangohrmangement.repositories;

import org.example.rangohrmangement.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
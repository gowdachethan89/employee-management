package com.employee.management.repository;

import com.employee.management.entity.Department;
import com.employee.management.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Optional<Employee> findByDepartmentName(String name);
}

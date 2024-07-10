package com.employee.management.repository;

import com.employee.management.entity.Department;
import com.employee.management.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByDepartment(Department department);

    Optional<Employee> findByEmail(String email);
}

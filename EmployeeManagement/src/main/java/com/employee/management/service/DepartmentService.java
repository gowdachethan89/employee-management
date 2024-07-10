package com.employee.management.service;

import com.employee.management.entity.Department;
import com.employee.management.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Department saveDepartment(Department department);
    Optional<Department> getDepartmentById(int id);
    List<Department> getAllDepartments();
    void deleteDepartment(int id);

    Optional<Employee> getDepartmentByName(String name);
}

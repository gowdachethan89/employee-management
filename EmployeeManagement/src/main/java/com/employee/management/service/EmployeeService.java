package com.employee.management.service;

import com.employee.management.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee saveEmployee(Employee employee); //create or update
    Optional<Employee> getEmployeeById(int id);
    List<Employee> getAllEmployees();
    void deleteEmployee(int id);
    List<Employee> getEmployeesByDepartment(int departmentId);
    Optional<Employee> getEmployeeByEmailId(String email);
}

package com.employee.management.service;

import com.employee.management.entity.Department;
import com.employee.management.entity.Employee;
import com.employee.management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getEmployeesByDepartment(int departmentId) {
        Department department = new Department();
        department.setDepartmentId(departmentId);
        return employeeRepository.findByDepartment(department);
    }

    @Override
    public Optional<Employee> getEmployeeByEmailId(String email) {
        return employeeRepository.findByEmail(email);
    }
}

package com.employee.management.service;

import com.employee.management.entity.Department;
import com.employee.management.entity.Employee;
import com.employee.management.repository.DepartmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
    DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Optional<Department> getDepartmentById(int id) {
        return departmentRepository.findById(id);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public void deleteDepartment(int id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Optional<Employee> getDepartmentByName(String name) {
        return departmentRepository.findByDepartmentName(name);
    }

    @Override
    public Department updateDepartment(Department departmentDetails, int id) throws Exception {
        Optional<Department> departmentById = departmentRepository.findById(id);
        if (departmentById.isPresent()){
            Department department = departmentById.get();
            department.setDepartmentName(departmentDetails.getDepartmentName());
            department.setEmployees(departmentDetails.getEmployees());
            department.setManager(departmentDetails.getManager());
            return department;
        }
        throw new Exception("Department Not Found");
    }
}

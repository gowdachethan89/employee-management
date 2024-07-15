package com.employee.management.controller;

import com.employee.management.entity.Department;
import com.employee.management.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @PostMapping
    public Department createDepartment(@RequestBody Department department){
        return departmentService.saveDepartment(department);
    }

    @GetMapping
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable int id) throws Exception {
        Optional<Department> departmentById = departmentService.getDepartmentById(id);
        if(departmentById.isPresent()){
            return departmentById.get();
        }
        throw new Exception("Department Not Found");
    }

    @PutMapping("/{id}")
    public Department updateDepartment(@RequestBody Department department, @PathVariable int id) throws Exception {
        return departmentService.updateDepartment(department, id);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable int id){
        departmentService.deleteDepartment(id);
    }
}

package com.employee.management.controller;

import com.employee.management.entity.Employee;
import com.employee.management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/{id}")
    public Employee getEmplogeeById(@PathVariable int id) throws Exception {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if (employee.isPresent()) {
            return employee.get();
        }
        throw new Exception("not Found");
    }
    @GetMapping
    public List<Employee> getAllEmployees() throws Exception {
        List<Employee> employees = employeeService.getAllEmployees();
        if (!employees.isEmpty()) {
            return employees;
        }
        throw new Exception("No Employees Found");
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable int id) throws Exception {
        return employeeService.updateEmployee(employee,id);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id) throws Exception {
        employeeService.deleteEmployee(id);
    }

}

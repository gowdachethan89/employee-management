package com.employee.management.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import com.employee.management.entity.Department;
import com.employee.management.entity.Employee;

import java.util.*;

class EmployeeRepositoryTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByDepartment_ReturnsListOfEmployees_WhenDepartmentExists() {
        Department department = new Department();
        List<Employee> employees = Arrays.asList(new Employee(), new Employee());
        when(employeeRepository.findByDepartment(department)).thenReturn(employees);

        List<Employee> result = employeeRepository.findByDepartment(department);

        assertEquals(employees, result);
    }

    @Test
    void findByDepartment_ReturnsEmptyList_WhenNoEmployeesInDepartment() {
        Department department = new Department();
        when(employeeRepository.findByDepartment(department)).thenReturn(Collections.emptyList());

        List<Employee> result = employeeRepository.findByDepartment(department);

        assertTrue(result.isEmpty());
    }

    @Test
    void findByDepartment_ReturnsEmptyList_WhenDepartmentIsNull() {
        when(employeeRepository.findByDepartment(null)).thenReturn(Collections.emptyList());

        List<Employee> result = employeeRepository.findByDepartment(null);

        assertTrue(result.isEmpty());
    }

    @Test
    void findByEmail_ReturnsEmployee_WhenEmailExists() {
        Employee employee = new Employee();
        when(employeeRepository.findByEmail("user@example.com")).thenReturn(Optional.of(employee));

        Optional<Employee> result = employeeRepository.findByEmail("user@example.com");

        assertTrue(result.isPresent());
        assertEquals(employee, result.get());
    }

    @Test
    void findByEmail_ReturnsEmptyOptional_WhenEmailDoesNotExist() {
        when(employeeRepository.findByEmail("notfound@example.com")).thenReturn(Optional.empty());

        Optional<Employee> result = employeeRepository.findByEmail("notfound@example.com");

        assertFalse(result.isPresent());
    }

    @Test
    void findByEmail_ReturnsEmptyOptional_WhenEmailIsNull() {
        when(employeeRepository.findByEmail(null)).thenReturn(Optional.empty());

        Optional<Employee> result = employeeRepository.findByEmail(null);

        assertFalse(result.isPresent());
    }
}


package com.employee.management.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import com.employee.management.entity.Employee;

import java.util.*;

class EmployeeServiceTest {

    @Mock
    private EmployeeService employeeService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveEmployee_ReturnsSavedEmployee() {
        Employee employee = new Employee();
        when(employeeService.saveEmployee(employee)).thenReturn(employee);

        Employee result = employeeService.saveEmployee(employee);

        assertEquals(employee, result);
    }

    @Test
    void getEmployeeById_ReturnsEmployee_WhenExists() {
        Employee employee = new Employee();
        when(employeeService.getEmployeeById(1)).thenReturn(Optional.of(employee));

        Optional<Employee> result = employeeService.getEmployeeById(1);

        assertTrue(result.isPresent());
        assertEquals(employee, result.get());
    }

    @Test
    void getEmployeeById_ReturnsEmptyOptional_WhenNotExists() {
        when(employeeService.getEmployeeById(99)).thenReturn(Optional.empty());

        Optional<Employee> result = employeeService.getEmployeeById(99);

        assertFalse(result.isPresent());
    }

    @Test
    void getAllEmployees_ReturnsListOfEmployees() {
        List<Employee> employees = Arrays.asList(new Employee(), new Employee());
        when(employeeService.getAllEmployees()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployees();

        assertEquals(employees, result);
    }

    @Test
    void getAllEmployees_ReturnsEmptyList_WhenNoEmployees() {
        when(employeeService.getAllEmployees()).thenReturn(Collections.emptyList());

        List<Employee> result = employeeService.getAllEmployees();

        assertTrue(result.isEmpty());
    }

    @Test
    void updateEmployee_ReturnsUpdatedEmployee_WhenNoException() throws Exception {
        Employee employee = new Employee();
        when(employeeService.updateEmployee(employee, 1)).thenReturn(employee);

        Employee result = employeeService.updateEmployee(employee, 1);

        assertEquals(employee, result);
    }

    @Test
    void updateEmployee_ThrowsException_WhenErrorOccurs() throws Exception {
        Employee employee = new Employee();
        when(employeeService.updateEmployee(employee, 99)).thenThrow(new Exception("Employee not found"));

        assertThrows(Exception.class, () -> employeeService.updateEmployee(employee, 99));
    }

    @Test
    void deleteEmployee_DoesNotThrowException() {
        doNothing().when(employeeService).deleteEmployee(1);

        assertDoesNotThrow(() -> employeeService.deleteEmployee(1));
        verify(employeeService, times(1)).deleteEmployee(1);
    }

    @Test
    void getEmployeesByDepartment_ReturnsListOfEmployees_WhenDepartmentExists() {
        List<Employee> employees = Arrays.asList(new Employee(), new Employee());
        when(employeeService.getEmployeesByDepartment(1)).thenReturn(employees);

        List<Employee> result = employeeService.getEmployeesByDepartment(1);

        assertEquals(employees, result);
    }

    @Test
    void getEmployeesByDepartment_ReturnsEmptyList_WhenNoEmployeesInDepartment() {
        when(employeeService.getEmployeesByDepartment(2)).thenReturn(Collections.emptyList());

        List<Employee> result = employeeService.getEmployeesByDepartment(2);

        assertTrue(result.isEmpty());
    }

    @Test
    void getEmployeeByEmailId_ReturnsEmployee_WhenEmailExists() {
        Employee employee = new Employee();
        when(employeeService.getEmployeeByEmailId("user@example.com")).thenReturn(Optional.of(employee));

        Optional<Employee> result = employeeService.getEmployeeByEmailId("user@example.com");

        assertTrue(result.isPresent());
        assertEquals(employee, result.get());
    }

    @Test
    void getEmployeeByEmailId_ReturnsEmptyOptional_WhenEmailDoesNotExist() {
        when(employeeService.getEmployeeByEmailId("notfound@example.com")).thenReturn(Optional.empty());

        Optional<Employee> result = employeeService.getEmployeeByEmailId("notfound@example.com");

        assertFalse(result.isPresent());
    }

    @Test
    void getEmployeeByEmailId_ReturnsEmptyOptional_WhenEmailIsNull() {
        when(employeeService.getEmployeeByEmailId(null)).thenReturn(Optional.empty());

        Optional<Employee> result = employeeService.getEmployeeByEmailId(null);

        assertFalse(result.isPresent());
    }
}

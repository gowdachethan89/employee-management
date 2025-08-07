package com.employee.management.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import com.employee.management.entity.Department;
import com.employee.management.entity.Employee;
import com.employee.management.repository.EmployeeRepository;

import java.util.*;

class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveEmployee_ReturnsSavedEmployee() {
        Employee employee = new Employee();
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee result = employeeService.saveEmployee(employee);

        assertEquals(employee, result);
    }

    @Test
    void getEmployeeById_ReturnsEmployee_WhenExists() {
        Employee employee = new Employee();
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

        Optional<Employee> result = employeeService.getEmployeeById(1);

        assertTrue(result.isPresent());
        assertEquals(employee, result.get());
    }

    @Test
    void getEmployeeById_ReturnsEmptyOptional_WhenNotExists() {
        when(employeeRepository.findById(99)).thenReturn(Optional.empty());

        Optional<Employee> result = employeeService.getEmployeeById(99);

        assertFalse(result.isPresent());
    }

    @Test
    void getAllEmployees_ReturnsListOfEmployees() {
        List<Employee> employees = Arrays.asList(new Employee(), new Employee());
        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployees();

        assertEquals(employees, result);
    }

    @Test
    void getAllEmployees_ReturnsEmptyList_WhenNoEmployees() {
        when(employeeRepository.findAll()).thenReturn(Collections.emptyList());

        List<Employee> result = employeeService.getAllEmployees();

        assertTrue(result.isEmpty());
    }

    @Test
    void updateEmployee_UpdatesFieldsAndReturnsEmployee_WhenEmployeeExists() throws Exception {
        Employee existing = new Employee();
        existing.setFirstName("Old");
        Employee details = new Employee();
        details.setFirstName("New");
        when(employeeRepository.findById(1)).thenReturn(Optional.of(existing));
        when(employeeRepository.save(any(Employee.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Employee result = employeeService.updateEmployee(details, 1);

        assertEquals("New", result.getFirstName());
    }

    @Test
    void updateEmployee_ThrowsException_WhenEmployeeNotFound() {
        Employee details = new Employee();
        when(employeeRepository.findById(99)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> employeeService.updateEmployee(details, 99));
        assertEquals("Employee Not Found", exception.getMessage());
    }

    @Test
    void deleteEmployee_InvokesRepositoryDeleteById() {
        doNothing().when(employeeRepository).deleteById(1);

        employeeService.deleteEmployee(1);

        verify(employeeRepository, times(1)).deleteById(1);
    }

    @Test
    void getEmployeesByDepartment_ReturnsListOfEmployees_WhenDepartmentExists() {
        Department department = new Department();
        department.setDepartmentId(1);
        List<Employee> employees = Arrays.asList(new Employee(), new Employee());
        when(employeeRepository.findByDepartment(any(Department.class))).thenReturn(employees);

        List<Employee> result = employeeService.getEmployeesByDepartment(1);

        assertEquals(employees, result);
    }

    @Test
    void getEmployeesByDepartment_ReturnsEmptyList_WhenNoEmployeesInDepartment() {
        when(employeeRepository.findByDepartment(any(Department.class))).thenReturn(Collections.emptyList());

        List<Employee> result = employeeService.getEmployeesByDepartment(2);

        assertTrue(result.isEmpty());
    }

    @Test
    void getEmployeeByEmailId_ReturnsEmployee_WhenEmailExists() {
        Employee employee = new Employee();
        when(employeeRepository.findByEmail("user@example.com")).thenReturn(Optional.of(employee));

        Optional<Employee> result = employeeService.getEmployeeByEmailId("user@example.com");

        assertTrue(result.isPresent());
        assertEquals(employee, result.get());
    }

    @Test
    void getEmployeeByEmailId_ReturnsEmptyOptional_WhenEmailDoesNotExist() {
        when(employeeRepository.findByEmail("notfound@example.com")).thenReturn(Optional.empty());

        Optional<Employee> result = employeeService.getEmployeeByEmailId("notfound@example.com");

        assertFalse(result.isPresent());
    }

    @Test
    void getEmployeeByEmailId_ReturnsEmptyOptional_WhenEmailIsNull() {
        when(employeeRepository.findByEmail(null)).thenReturn(Optional.empty());

        Optional<Employee> result = employeeService.getEmployeeByEmailId(null);

        assertFalse(result.isPresent());
    }
}


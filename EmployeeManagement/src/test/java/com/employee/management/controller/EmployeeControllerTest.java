package com.employee.management.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.employee.management.entity.Employee;
import com.employee.management.service.EmployeeService;

import java.util.*;

class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    void createEmployee_ReturnsSavedEmployee() {
        Employee employee = new Employee();
        when(employeeService.saveEmployee(any(Employee.class))).thenReturn(employee);

        Employee result = employeeController.createEmployee(employee);

        assertEquals(employee, result);
    }

    @Test
    void getEmplogeeById_ReturnsEmployee_WhenFound() throws Exception {
        Employee employee = new Employee();
        when(employeeService.getEmployeeById(1)).thenReturn(Optional.of(employee));

        Employee result = employeeController.getEmplogeeById(1);

        assertEquals(employee, result);
    }

    @Test
    void getEmplogeeById_ThrowsException_WhenNotFound() {
        when(employeeService.getEmployeeById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> employeeController.getEmplogeeById(1));
        assertEquals("not Found", exception.getMessage());
    }

    @Test
    void getAllEmployees_ReturnsList_WhenEmployeesExist() throws Exception {
        List<Employee> employees = Arrays.asList(new Employee(), new Employee());
        when(employeeService.getAllEmployees()).thenReturn(employees);

        List<Employee> result = employeeController.getAllEmployees();

        assertEquals(employees, result);
    }

    @Test
    void getAllEmployees_ThrowsException_WhenNoEmployees() {
        when(employeeService.getAllEmployees()).thenReturn(Collections.emptyList());

        Exception exception = assertThrows(Exception.class, () -> employeeController.getAllEmployees());
        assertEquals("No Employees Found", exception.getMessage());
    }

    @Test
    void updateEmployee_ReturnsUpdatedEmployee() throws Exception {
        Employee employee = new Employee();
        when(employeeService.updateEmployee(any(Employee.class), eq(1))).thenReturn(employee);

        Employee result = employeeController.updateEmployee(employee, 1);

        assertEquals(employee, result);
    }

    @Test
    void deleteEmployee_InvokesServiceDelete() throws Exception {
        doNothing().when(employeeService).deleteEmployee(1);

        assertDoesNotThrow(() -> employeeController.deleteEmployee(1));
        verify(employeeService, times(1)).deleteEmployee(1);
    }
}


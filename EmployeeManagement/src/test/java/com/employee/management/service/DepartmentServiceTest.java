package com.employee.management.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import com.employee.management.entity.Department;
import com.employee.management.entity.Employee;

import java.util.*;

class DepartmentServiceTest {

    @Mock
    private DepartmentService departmentService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveDepartment_ReturnsSavedDepartment() {
        Department department = new Department();
        when(departmentService.saveDepartment(department)).thenReturn(department);

        Department result = departmentService.saveDepartment(department);

        assertEquals(department, result);
    }

    @Test
    void getDepartmentById_ReturnsDepartment_WhenExists() {
        Department department = new Department();
        when(departmentService.getDepartmentById(1)).thenReturn(Optional.of(department));

        Optional<Department> result = departmentService.getDepartmentById(1);

        assertTrue(result.isPresent());
        assertEquals(department, result.get());
    }

    @Test
    void getDepartmentById_ReturnsEmptyOptional_WhenNotExists() {
        when(departmentService.getDepartmentById(99)).thenReturn(Optional.empty());

        Optional<Department> result = departmentService.getDepartmentById(99);

        assertFalse(result.isPresent());
    }

    @Test
    void getAllDepartments_ReturnsListOfDepartments() {
        List<Department> departments = Arrays.asList(new Department(), new Department());
        when(departmentService.getAllDepartments()).thenReturn(departments);

        List<Department> result = departmentService.getAllDepartments();

        assertEquals(departments, result);
    }

    @Test
    void getAllDepartments_ReturnsEmptyList_WhenNoDepartments() {
        when(departmentService.getAllDepartments()).thenReturn(Collections.emptyList());

        List<Department> result = departmentService.getAllDepartments();

        assertTrue(result.isEmpty());
    }

    @Test
    void deleteDepartment_DoesNotThrowException() {
        doNothing().when(departmentService).deleteDepartment(1);

        assertDoesNotThrow(() -> departmentService.deleteDepartment(1));
        verify(departmentService, times(1)).deleteDepartment(1);
    }

    @Test
    void getDepartmentByName_ReturnsEmployee_WhenDepartmentExists() {
        Employee employee = new Employee();
        when(departmentService.getDepartmentByName("Finance")).thenReturn(Optional.of(employee));

        Optional<Employee> result = departmentService.getDepartmentByName("Finance");

        assertTrue(result.isPresent());
        assertEquals(employee, result.get());
    }

    @Test
    void getDepartmentByName_ReturnsEmptyOptional_WhenDepartmentDoesNotExist() {
        when(departmentService.getDepartmentByName("NonExistent")).thenReturn(Optional.empty());

        Optional<Employee> result = departmentService.getDepartmentByName("NonExistent");

        assertFalse(result.isPresent());
    }

    @Test
    void updateDepartment_ReturnsUpdatedDepartment_WhenNoException() throws Exception {
        Department department = new Department();
        when(departmentService.updateDepartment(department, 1)).thenReturn(department);

        Department result = departmentService.updateDepartment(department, 1);

        assertEquals(department, result);
    }

    @Test
    void updateDepartment_ThrowsException_WhenErrorOccurs() throws Exception {
        Department department = new Department();
        when(departmentService.updateDepartment(department, 99)).thenThrow(new Exception("Department not found"));

        assertThrows(Exception.class, () -> departmentService.updateDepartment(department, 99));
    }
}


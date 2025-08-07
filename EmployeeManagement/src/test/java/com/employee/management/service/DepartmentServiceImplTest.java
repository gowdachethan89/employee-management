package com.employee.management.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.employee.management.entity.Department;
import com.employee.management.entity.Employee;
import java.util.*;

import com.employee.management.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DepartmentServiceImplTest {

    private DepartmentRepository departmentRepository;
    private DepartmentServiceImpl departmentService;

    @BeforeEach
    void setUp() {
        departmentRepository = mock(DepartmentRepository.class);
        departmentService = new DepartmentServiceImpl();
        departmentService.departmentRepository = departmentRepository;
    }

    @Test
    @DisplayName("Save department returns saved department")
    void saveDepartmentReturnsSavedDepartment() {
        Department department = new Department();
        when(departmentRepository.save(department)).thenReturn(department);

        Department result = departmentService.saveDepartment(department);

        assertEquals(department, result);
        verify(departmentRepository).save(department);
    }

    @Test
    @DisplayName("Get department by id returns department when found")
    void getDepartmentByIdReturnsDepartmentWhenFound() {
        Department department = new Department();
        when(departmentRepository.findById(1)).thenReturn(Optional.of(department));

        Optional<Department> result = departmentService.getDepartmentById(1);

        assertTrue(result.isPresent());
        assertEquals(department, result.get());
    }

    @Test
    @DisplayName("Get department by id returns empty when not found")
    void getDepartmentByIdReturnsEmptyWhenNotFound() {
        when(departmentRepository.findById(1)).thenReturn(Optional.empty());

        Optional<Department> result = departmentService.getDepartmentById(1);

        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Get all departments returns list of departments")
    void getAllDepartmentsReturnsListOfDepartments() {
        List<Department> departments = Arrays.asList(new Department(), new Department());
        when(departmentRepository.findAll()).thenReturn(departments);

        List<Department> result = departmentService.getAllDepartments();

        assertEquals(departments, result);
    }

    @Test
    @DisplayName("Delete department calls repository deleteById")
    void deleteDepartmentCallsRepositoryDeleteById() {
        departmentService.deleteDepartment(1);

        verify(departmentRepository).deleteById(1);
    }

    @Test
    @DisplayName("Get department by name returns employee when found")
    void getDepartmentByNameReturnsEmployeeWhenFound() {
        Optional<Employee> employee = Optional.of(new Employee());
        when(departmentRepository.findByDepartmentName("HR")).thenReturn(employee);

        Optional<Employee> result = departmentService.getDepartmentByName("HR");

        assertTrue(result.isPresent());
        assertEquals(employee, result);
    }

    @Test
    @DisplayName("Get department by name returns empty when not found")
    void getDepartmentByNameReturnsEmptyWhenNotFound() {
        when(departmentRepository.findByDepartmentName("Finance")).thenReturn(Optional.empty());

        Optional<Employee> result = departmentService.getDepartmentByName("Finance");

        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Update department updates and returns department when found")
    void updateDepartmentUpdatesAndReturnsDepartmentWhenFound() throws Exception {
        Department existing = new Department();
        Department details = new Department();
        details.setDepartmentName("New Name");
        details.setEmployees(new ArrayList<>());
        details.setManager(new Employee());

        when(departmentRepository.findById(1)).thenReturn(Optional.of(existing));

        Department result = departmentService.updateDepartment(details, 1);

        assertEquals("New Name", result.getDepartmentName());
        assertEquals(details.getEmployees(), result.getEmployees());
        assertEquals(details.getManager(), result.getManager());
    }

    @Test
    @DisplayName("Update department throws exception when department not found")
    void updateDepartmentThrowsExceptionWhenDepartmentNotFound() {
        Department details = new Department();
        when(departmentRepository.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> {
            departmentService.updateDepartment(details, 1);
        });

        assertEquals("Department Not Found", exception.getMessage());
    }
}



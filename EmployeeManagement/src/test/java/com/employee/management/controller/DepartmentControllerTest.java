package com.employee.management.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import com.employee.management.entity.Department;
import com.employee.management.service.DepartmentService;

import java.util.*;

class DepartmentControllerTest {

    @InjectMocks
    private DepartmentController departmentController;

    @Mock
    private DepartmentService departmentService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createDepartment_ReturnsSavedDepartment() {
        Department department = new Department();
        when(departmentService.saveDepartment(any(Department.class))).thenReturn(department);

        Department result = departmentController.createDepartment(department);

        assertEquals(department, result);
    }

    @Test
    void getAllDepartments_ReturnsList_WhenDepartmentsExist() {
        List<Department> departments = Arrays.asList(new Department(), new Department());
        when(departmentService.getAllDepartments()).thenReturn(departments);

        List<Department> result = departmentController.getAllDepartments();

        assertEquals(departments, result);
    }

    @Test
    void getAllDepartments_ReturnsEmptyList_WhenNoDepartmentsExist() {
        when(departmentService.getAllDepartments()).thenReturn(Collections.emptyList());

        List<Department> result = departmentController.getAllDepartments();

        assertTrue(result.isEmpty());
    }

    @Test
    void getDepartmentById_ReturnsDepartment_WhenFound() throws Exception {
        Department department = new Department();
        when(departmentService.getDepartmentById(1)).thenReturn(Optional.of(department));

        Department result = departmentController.getDepartmentById(1);

        assertEquals(department, result);
    }

    @Test
    void getDepartmentById_ThrowsException_WhenNotFound() {
        when(departmentService.getDepartmentById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> departmentController.getDepartmentById(1));
        assertEquals("Department Not Found", exception.getMessage());
    }

    @Test
    void updateDepartment_ReturnsUpdatedDepartment() throws Exception {
        Department department = new Department();
        when(departmentService.updateDepartment(any(Department.class), eq(1))).thenReturn(department);

        Department result = departmentController.updateDepartment(department, 1);

        assertEquals(department, result);
    }

    @Test
    void deleteDepartment_InvokesServiceDelete() {
        doNothing().when(departmentService).deleteDepartment(1);

        assertDoesNotThrow(() -> departmentController.deleteDepartment(1));
        verify(departmentService, times(1)).deleteDepartment(1);
    }
}


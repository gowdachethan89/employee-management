package com.employee.management.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import com.employee.management.entity.Employee;

import java.util.*;

class DepartmentRepositoryTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByDepartmentName_ReturnsEmployee_WhenDepartmentExists() {
        Employee employee = new Employee();
        when(departmentRepository.findByDepartmentName("HR")).thenReturn(Optional.of(employee));

        Optional<Employee> result = departmentRepository.findByDepartmentName("HR");

        assertTrue(result.isPresent());
        assertEquals(employee, result.get());
    }

    @Test
    void findByDepartmentName_ReturnsEmptyOptional_WhenDepartmentDoesNotExist() {
        when(departmentRepository.findByDepartmentName("NonExistent")).thenReturn(Optional.empty());

        Optional<Employee> result = departmentRepository.findByDepartmentName("NonExistent");

        assertFalse(result.isPresent());
    }

    @Test
    void findByDepartmentName_ReturnsEmptyOptional_WhenNameIsNull() {
        when(departmentRepository.findByDepartmentName(null)).thenReturn(Optional.empty());

        Optional<Employee> result = departmentRepository.findByDepartmentName(null);

        assertFalse(result.isPresent());
    }
}


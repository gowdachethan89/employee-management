package com.employee.management.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.*;

class DepartmentTest {

    @Test
    void departmentObject_CanBeCreatedWithNoArgsConstructor() {
        Department department = new Department();
        assertNotNull(department);
    }

    @Test
    void setAndGetDepartmentId_WorksCorrectly() {
        Department department = new Department();
        department.setDepartmentId(5);
        assertEquals(5, department.getDepartmentId());
    }

    @Test
    void setAndGetDepartmentName_WorksCorrectly() {
        Department department = new Department();
        department.setDepartmentName("Finance");
        assertEquals("Finance", department.getDepartmentName());
    }

    @Test
    void setAndGetManager_WorksCorrectly() {
        Department department = new Department();
        Employee manager = new Employee();
        department.setManager(manager);
        assertEquals(manager, department.getManager());
    }

    @Test
    void setAndGetEmployees_WorksCorrectly() {
        Department department = new Department();
        Employee e1 = new Employee();
        Employee e2 = new Employee();
        List<Employee> employees = Arrays.asList(e1, e2);
        department.setEmployees(employees);
        assertEquals(employees, department.getEmployees());
    }

    @Test
    void employees_AllowsNullValue() {
        Department department = new Department();
        department.setEmployees(null);
        assertNull(department.getEmployees());
    }

    @Test
    void manager_AllowsNullValue() {
        Department department = new Department();
        department.setManager(null);
        assertNull(department.getManager());
    }
}

package com.employee.management.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.time.LocalDate;
import java.util.*;

class EmployeeTest {

    @Test
    void employeeObject_CanBeCreatedWithNoArgsConstructor() {
        Employee employee = new Employee();
        assertNotNull(employee);
    }

    @Test
    void setAndGetEmployeeId_WorksCorrectly() {
        Employee employee = new Employee();
        employee.setEmployeeId(42);
        assertEquals(42, employee.getEmployeeId());
    }

    @Test
    void setAndGetFirstName_WorksCorrectly() {
        Employee employee = new Employee();
        employee.setFirstName("John");
        assertEquals("John", employee.getFirstName());
    }

    @Test
    void setAndGetLastName_WorksCorrectly() {
        Employee employee = new Employee();
        employee.setLastName("Doe");
        assertEquals("Doe", employee.getLastName());
    }

    @Test
    void setAndGetEmail_WorksCorrectly() {
        Employee employee = new Employee();
        employee.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", employee.getEmail());
    }

    @Test
    void setAndGetPhone_WorksCorrectly() {
        Employee employee = new Employee();
        employee.setPhone("1234567890");
        assertEquals("1234567890", employee.getPhone());
    }

    @Test
    void setAndGetAddress_WorksCorrectly() {
        Employee employee = new Employee();
        employee.setAddress("123 Main St");
        assertEquals("123 Main St", employee.getAddress());
    }

    @Test
    void setAndGetDateOfBirth_WorksCorrectly() {
        Employee employee = new Employee();
        LocalDate dob = LocalDate.of(1990, 1, 1);
        employee.setDateOfBirth(dob);
        assertEquals(dob, employee.getDateOfBirth());
    }

    @Test
    void setAndGetHireDate_WorksCorrectly() {
        Employee employee = new Employee();
        LocalDate hireDate = LocalDate.of(2020, 5, 10);
        employee.setHireDate(hireDate);
        assertEquals(hireDate, employee.getHireDate());
    }

    @Test
    void setAndGetDepartment_WorksCorrectly() {
        Employee employee = new Employee();
        Department department = new Department();
        employee.setDepartment(department);
        assertEquals(department, employee.getDepartment());
    }

    @Test
    void setAndGetPosition_WorksCorrectly() {
        Employee employee = new Employee();
        employee.setPosition("Developer");
        assertEquals("Developer", employee.getPosition());
    }

    @Test
    void setAndGetSalary_WorksCorrectly() {
        Employee employee = new Employee();
        employee.setSalary(75000.50);
        assertEquals(75000.50, employee.getSalary());
    }

    @Test
    void setAndGetManager_WorksCorrectly() {
        Employee employee = new Employee();
        Employee manager = new Employee();
        employee.setManager(manager);
        assertEquals(manager, employee.getManager());
    }

    @Test
    void setAndGetSubordinates_WorksCorrectly() {
        Employee employee = new Employee();
        Employee subordinate1 = new Employee();
        Employee subordinate2 = new Employee();
        List<Employee> subordinates = Arrays.asList(subordinate1, subordinate2);
        employee.setSubordinates(subordinates);
        assertEquals(subordinates, employee.getSubordinates());
    }

    @Test
    void subordinates_AllowsNullValue() {
        Employee employee = new Employee();
        employee.setSubordinates(null);
        assertNull(employee.getSubordinates());
    }

    @Test
    void manager_AllowsNullValue() {
        Employee employee = new Employee();
        employee.setManager(null);
        assertNull(employee.getManager());
    }

    @Test
    void department_AllowsNullValue() {
        Employee employee = new Employee();
        employee.setDepartment(null);
        assertNull(employee.getDepartment());
    }
}


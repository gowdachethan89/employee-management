package com.employee.management.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.time.LocalDate;
import java.time.LocalTime;

class AttendanceTest {

    @Test
    void attendanceObject_CanBeCreatedWithNoArgsConstructor() {
        Attendance attendance = new Attendance();
        assertNotNull(attendance);
    }

    @Test
    void setAndGetAttendanceId_WorksCorrectly() {
        Attendance attendance = new Attendance();
        attendance.setAttendanceId(10);
        assertEquals(10, attendance.getAttendanceId());
    }

    @Test
    void setAndGetEmployee_WorksCorrectly() {
        Attendance attendance = new Attendance();
        Employee employee = new Employee();
        attendance.setEmployee(employee);
        assertEquals(employee, attendance.getEmployee());
    }

    @Test
    void setAndGetDate_WorksCorrectly() {
        Attendance attendance = new Attendance();
        LocalDate date = LocalDate.of(2024, 6, 1);
        attendance.setDate(date);
        assertEquals(date, attendance.getDate());
    }

    @Test
    void setAndGetStatus_WorksCorrectly() {
        Attendance attendance = new Attendance();
        attendance.setStatus("Present");
        assertEquals("Present", attendance.getStatus());
    }

    @Test
    void setAndGetCheckInTime_WorksCorrectly() {
        Attendance attendance = new Attendance();
        LocalTime checkIn = LocalTime.of(9, 0);
        attendance.setCheckInTime(checkIn);
        assertEquals(checkIn, attendance.getCheckInTime());
    }

    @Test
    void setAndGetCheckOutTime_WorksCorrectly() {
        Attendance attendance = new Attendance();
        LocalTime checkOut = LocalTime.of(18, 0);
        attendance.setCheckOutTime(checkOut);
        assertEquals(checkOut, attendance.getCheckOutTime());
    }

    @Test
    void status_AllowsNullValue() {
        Attendance attendance = new Attendance();
        attendance.setStatus(null);
        assertNull(attendance.getStatus());
    }

    @Test
    void checkInAndCheckOutTime_AllowNullValues() {
        Attendance attendance = new Attendance();
        attendance.setCheckInTime(null);
        attendance.setCheckOutTime(null);
        assertNull(attendance.getCheckInTime());
        assertNull(attendance.getCheckOutTime());
    }
}


package com.employee.management.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import com.employee.management.entity.Attendance;

import java.util.*;

class AttendanceRepositoryTest {

    @Mock
    private AttendanceRepository attendanceRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById_ReturnsAttendance_WhenAttendanceExists() {
        Attendance attendance = new Attendance();
        when(attendanceRepository.findById(1)).thenReturn(Optional.of(attendance));

        Optional<Attendance> result = attendanceRepository.findById(1);

        assertTrue(result.isPresent());
        assertEquals(attendance, result.get());
    }

    @Test
    void findById_ReturnsEmptyOptional_WhenAttendanceDoesNotExist() {
        when(attendanceRepository.findById(99)).thenReturn(Optional.empty());

        Optional<Attendance> result = attendanceRepository.findById(99);

        assertFalse(result.isPresent());
    }

    @Test
    void save_SavesAndReturnsAttendance() {
        Attendance attendance = new Attendance();
        when(attendanceRepository.save(attendance)).thenReturn(attendance);

        Attendance result = attendanceRepository.save(attendance);

        assertEquals(attendance, result);
    }

    @Test
    void findAll_ReturnsListOfAttendances() {
        List<Attendance> attendances = Arrays.asList(new Attendance(), new Attendance());
        when(attendanceRepository.findAll()).thenReturn(attendances);

        List<Attendance> result = attendanceRepository.findAll();

        assertEquals(attendances, result);
    }

    @Test
    void deleteById_DeletesAttendance() {
        doNothing().when(attendanceRepository).deleteById(1);

        assertDoesNotThrow(() -> attendanceRepository.deleteById(1));
        verify(attendanceRepository, times(1)).deleteById(1);
    }
}


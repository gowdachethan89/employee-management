package com.employee.management;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
class EmployeeManagementApplicationTest {
	
	@Test
		void contextLoads() {
	}

	@Test
    void main_InvokesSpringApplicationRun() {
        try (MockedStatic<SpringApplication> mocked = org.mockito.Mockito.mockStatic(SpringApplication.class)) {
            mocked.when(() -> SpringApplication.run(EmployeeManagementApplication.class, new String[]{}))
                  .thenReturn(null);

            EmployeeManagementApplication.main(new String[]{});

            mocked.verify(() -> SpringApplication.run(EmployeeManagementApplication.class, new String[]{}));
        }
    }
}

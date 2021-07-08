package com.jperoutek.staffingproject;

import com.jperoutek.staffingproject.controller.EmployeeController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class StaffingProjectApplicationTest {
    @Autowired
    private EmployeeController employeeController;

    @Test
    public void testContextLoads() throws Exception{
        assertThat(employeeController).isNotNull();
    }
}

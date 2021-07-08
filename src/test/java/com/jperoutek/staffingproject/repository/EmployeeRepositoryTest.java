package com.jperoutek.staffingproject.repository;

import com.jperoutek.staffingproject.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTest {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(employeeRepository).isNotNull();
        assertThat(testEntityManager).isNotNull();
    }

    @Test
    public void shouldStoreEmployee() {
        Employee employee = employeeRepository.save(mockEmployee());
        assertThat(employee).hasFieldOrPropertyWithValue("firstName", "Peter");
        assertThat(employee).hasFieldOrPropertyWithValue("lastName", "Parker");
        assertThat(employee).hasFieldOrPropertyWithValue("department", "Reporting");
    }

    public Employee mockEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("Peter");
        employee.setLastName("Parker");
        employee.setDepartment("Reporting");
        employee.setJobDescription("Taking pictures and stuff");
        return employee;
    }
}

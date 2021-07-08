package com.jperoutek.staffingproject.service;

import com.jperoutek.staffingproject.model.Employee;
import com.jperoutek.staffingproject.repository.EmployeeRepository;
import com.jperoutek.staffingproject.utility.ApplicationUtil;
import com.jperoutek.staffingproject.utility.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EmployeeService {
    private final Logger log = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    EmployeeRepository employeeRepository;

    public GenericResponse createEmployee(Employee employee) {
        GenericResponse response = new GenericResponse();
        // Validate that the Employee has all necessary fields
        if (employee.getFirstName() == null      || employee.getFirstName().isEmpty()      ||
            employee.getLastName() == null       || employee.getLastName().isEmpty()       ||
            employee.getDepartment() == null     || employee.getDepartment().isEmpty()     ||
            employee.getJobDescription() == null || employee.getJobDescription().isEmpty()) {
            response = GenericResponse.createErrorResponse("None of the fields in Employee can have length 0");
            return response;
        }

        response.setResponse(employeeRepository.save(employee));
        return response;
    }

    public GenericResponse readAllEmployees() {
        GenericResponse response = GenericResponse.createSuccessResponse();
        try {
            List<Employee> employees = StreamSupport
                    .stream(employeeRepository.findAll().spliterator(), false)
                    .collect(Collectors.toList());
            if (employees.isEmpty()) {
                response.addMessage("No employees found!");
                return response;
            }
            response.setResponse(employees);
            return response;
        }
        catch (Exception e) {
            response = GenericResponse.createErrorResponse(
                    "Exception occurred while storing in the repository : "+e.getMessage());
            log.error("Exception occurred while storing in the repository : "+e.getMessage());
            e.printStackTrace();
            return response;
        }

    }

    public GenericResponse readEmployee(Long empID) {
        GenericResponse response = GenericResponse.createSuccessResponse();
        try {
            Optional<Employee> employee = employeeRepository.findById(empID);
            employee.ifPresent(response::setResponse);

            if (employee.isEmpty()) {
                response.addMessage("No employees found with id: " + empID);
            }

            return response;

        } catch (Exception e) {
            response = GenericResponse.createErrorResponse(
                    "Exception occurred while storing in the repository : "+e.getMessage());
            log.error("Exception occurred while storing in the repository : "+e.getMessage());
            e.printStackTrace();
            return response;
        }
    }

    public GenericResponse deleteEmployee(Long empID) {
        GenericResponse response = GenericResponse.createSuccessResponse();
        try {
            employeeRepository.deleteById(empID);
            response.addMessage(String.format("Employee with ID = %d has been deleted.", empID));
            return response;
        } catch (Exception e) {
            response = GenericResponse.createErrorResponse(
                    "Exception occurred while storing in the repository : "+e.getMessage());
            log.error("Exception occurred while storing in the repository : "+e.getMessage());
            e.printStackTrace();
            return response;
        }
    }

    public GenericResponse updateEmployee(Employee employee, Long empID) {
        GenericResponse response = GenericResponse.createSuccessResponse();
        try {
            Optional<Employee> currentEmployee = employeeRepository.findById(empID);
            if (currentEmployee.isPresent()) {
                Employee updatedEmployee = currentEmployee.get();
                ApplicationUtil.copyProperties(employee, updatedEmployee);
                updatedEmployee.setId(empID);
                response.setResponse(employeeRepository.save(updatedEmployee));
                return response;
            } else {
                return GenericResponse.createErrorResponse(
                        String.format("id: %d doesn't exist in the database!", empID));
            }
        } catch (Exception e) {
            response = GenericResponse.createErrorResponse(
                    "Exception occurred while storing in the repository : "+e.getMessage());
            log.error("Exception occurred while storing in the repository : "+e.getMessage());
            e.printStackTrace();
            return response;
        }
    }

}

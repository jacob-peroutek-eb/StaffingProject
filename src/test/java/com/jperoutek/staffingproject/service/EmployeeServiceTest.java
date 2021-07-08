package com.jperoutek.staffingproject.service;

import com.jperoutek.staffingproject.model.Employee;
import com.jperoutek.staffingproject.repository.EmployeeRepository;
import com.jperoutek.staffingproject.utility.GenericResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void shouldSavePayLoadWhenValidObjectIsPassed(){
        Employee employee = mockPayLoad("");
        Employee responseEmployee = mockPayLoad("");
        when(employeeRepository.save(any(Employee.class))).thenReturn(responseEmployee);
        GenericResponse genericResponse = employeeService.createEmployee(employee);
        Assert.assertTrue(genericResponse.getSuccess());
        Assert.assertEquals(genericResponse.getResponse(), responseEmployee);
    }


    @Test
    public void shouldNotSavePayLoadWhenInValidObjectIsPassed(){
        Employee employee = mockPayLoad("invalid");
        GenericResponse response = employeeService.createEmployee(employee);
        Assert.assertFalse(response.getSuccess());
        Assert.assertNull(response.getResponse());
    }

//    @Test
//    public void shouldReturnPayCodesOnValidBridgeConfigId(){
//        Employee employee = mockPayLoad("");
//        List<Employee> employeeList = new ArrayList<>(Arrays.asList(employee));
//        when(employeeRepository.findById(any(Long.class))).thenReturn(employeeList);
//        GenericResponse genericResponse = employeeService.readEmployee(1L);
//        Assert.assertTrue(genericResponse.getSuccess());
//        Assert.assertTrue(((ArrayList<Employee>)genericResponse.getResponse()).equals(employeeList));
//    }

//    @Test(expected = IllegalArgumentException.class)
//    public void shouldThrowExceptionOnInvalidBridgeConfigId() throws IllegalArgumentException{
//        when(employeeRepository.findAllByBridgeConfigId(UUID.fromString("Invalid"))).thenThrow(new IllegalArgumentException());
//        GenericResponse genericResponse = payCodeService.retrievePayCodes("Invalid");
//        Assert.assertFalse(genericResponse.getSuccess());
//        Assert.assertTrue(Objects.isNull(genericResponse.getResponse()));
//    }
//
//    @Test
//    public void shouldReturnEmptyListOnInValidBridgeConfigId(){
//        List<PayCode> payCodes = null;
//        when(employeeRepository.findAllByBridgeConfigId(any(UUID.class))).thenReturn(payCodes);
//        GenericResponse genericResponse = payCodeService.retrievePayCodes("e53f34f2-e265-49a7-bf05-c6b594959348");
//        Assert.assertFalse(genericResponse.getSuccess());
//        Assert.assertTrue(Objects.isNull(genericResponse.getResponse()));
//    }
//
//    @Test
//    public void shouldUpdatePayCodeWhenValidObjectIsPassed(){
//        PayCode payCode = mockPayLoad("dbwrite");
//        PayCode responsePayCode = payCode;
//        responsePayCode.setPay(true);
//        when(employeeRepository.findByPayCodeId(payCode.getPayCodeId())).thenReturn(Optional.ofNullable(payCode));
//        when(employeeRepository.save(any(PayCode.class))).thenReturn(responsePayCode);
//        GenericResponse genericResponse = payCodeService.updatePayCodeMap(mockPayCodeDTO("raw"),payCode.getPayCodeId().toString());
//        Assert.assertTrue(genericResponse.getSuccess());
//        Assert.assertTrue(genericResponse.getResponse().equals(responsePayCode));
//    }
//
//    @Test
//    public void shouldDeletePayCodeWhenValidObjectIsPassed(){
//        PayCode payCode = mockPayLoad("dbwrite");
//        PayCode responsePayCode = payCode;
//        responsePayCode.setPay(true);
//        responsePayCode.setPayCodeId(UUID.fromString("e53f34f2-e265-49a7-bf05-c6b594959349"));
//        when(employeeRepository.findByPayCodeId(payCode.getPayCodeId())).thenReturn(Optional.ofNullable(payCode));
//        when(employeeRepository.findAllByBridgeConfigId(payCode.getBridgeConfigId())).thenReturn(Arrays.asList(payCode,mockPayLoad("dbwrite")));
//        employeeRepository.delete(Mockito.any());
//        verify(employeeRepository, times(1)).delete(Mockito.any());
//        GenericResponse genericResponse = payCodeService.deletePayCode("e53f34f2-e265-49a7-bf05-c6b594959349");
//        Assert.assertTrue(genericResponse.getSuccess());
//    }
//
//
//
//    @Test
//    public void shouldUpdatePayCodeWhenInvalidObjectIsPassed(){
//        UUID payCodeId = UUID.randomUUID();
//        when(employeeRepository.findByPayCodeId(payCodeId)).thenReturn(null);
//        GenericResponse genericResponse = payCodeService.updatePayCodeMap(mockPayCodeDTO("raw"),payCodeId.toString());
//        Assert.assertFalse(genericResponse.getSuccess());
//    }

    public Employee mockPayLoad(String key){
        Employee employee = new Employee();
        if (! key.equals("invalid")) {
            employee.setId(1L);
            employee.setFirstName("John");
            employee.setLastName("Deere");
            employee.setDepartment("Testing");
            employee.setJobDescription("Testing Java microservices");
        }
        return employee;
    }
}

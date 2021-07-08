package com.jperoutek.staffingproject.controller;

import com.jperoutek.staffingproject.model.Employee;
import com.jperoutek.staffingproject.service.EmployeeService;
import com.jperoutek.staffingproject.utility.GenericResponse;
import net.bytebuddy.description.type.TypeList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {
    private final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;

    private GenericResponse response;

    @PostMapping
    public ResponseEntity<GenericResponse> createEmployee(@Valid @RequestBody Employee employee ){
        try{
            response = employeeService.createEmployee(employee);
            if(response.getSuccess())
                return new ResponseEntity<>(response, HttpStatus.OK);
            else
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            response = GenericResponse.createErrorResponse(
                    "Exception occurred while processing the request :" + e.getMessage());
            log.error("Exception occurred while processing the request :" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<GenericResponse> readAllEmployees() {
        try {
            response = employeeService.readAllEmployees();
            if (response.getSuccess())
                return new ResponseEntity<>(response, HttpStatus.OK);
            else
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            response = GenericResponse.createErrorResponse(
                    "Exception occurred while processing the request :" + e.getMessage());
            log.error("Exception occurred while processing the request :" + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{empID}")
    public ResponseEntity<GenericResponse> readEmployee(@PathVariable(value="empID") String id_string) {
        Long id = Long.parseLong(id_string, 10);
        response = employeeService.readEmployee(id);
        if (response.getSuccess())
            return new ResponseEntity<>(response, HttpStatus.OK);
        else
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{empID}")
    public ResponseEntity<GenericResponse> deleteEmployee(@PathVariable(value="empID") String id_string) {
        Long id = Long.parseLong(id_string, 10);
        response = employeeService.deleteEmployee(id);
        if (response.getSuccess())
            return new ResponseEntity<>(response, HttpStatus.OK);
        else
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/{empID}")
    public ResponseEntity<GenericResponse> updateEmployee(
            @Valid @RequestBody Employee employee,
            @PathVariable(value="empID") String id_string) {
        Long id = Long.parseLong(id_string, 10);
        response = employeeService.updateEmployee(employee, id);
        if (response.getSuccess())
            return new ResponseEntity<>(response, HttpStatus.OK);
        else
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

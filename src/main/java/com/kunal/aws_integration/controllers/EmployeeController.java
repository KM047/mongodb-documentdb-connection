package com.kunal.aws_integration.controllers;

import com.kunal.aws_integration.model.Employee;
import com.kunal.aws_integration.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emp")
public class EmployeeController {


    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


     @GetMapping
     public List<Employee> getAllEmployees() {
         return employeeService.getAllEmployees();
     }

    @GetMapping("/{empID}")
    public Employee getEmployeeById(@PathVariable String empID) {
        return employeeService.getEmployee(empID);
    }

     @PostMapping
     public Employee createEmployee(@RequestBody Employee employee) {
         return employeeService.saveEmployee(employee);
     }

    @PutMapping("/{empID}")
    public Employee updateEmployee(@PathVariable String empID, @RequestBody Employee updatedEmployee) {
        return employeeService.updateEmployee(updatedEmployee, empID);
    }

    @DeleteMapping("/{empID}")
    public void deleteEmployee(@PathVariable String empID) {
        employeeService.deleteEmployee(empID);
    }


}

package com.kunal.aws_integration.controllers;

import com.kunal.aws_integration.model.Employee;
import com.kunal.aws_integration.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emp")
@Tag(
        name = "Employee API",
        description = "This API allows you to perform CRUD operations on Employee data. " +
                "You can create, read, update, and delete employee records."
)
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "2. Get all employees", description = "Fetches the complete list of all employees.")
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @Operation(summary = "3. Get employee by ID", description = "Fetches a single employee by their ID.")
    @GetMapping("/{empID}")
    public Employee getEmployeeById(@PathVariable String empID) {
        return employeeService.getEmployee(empID);
    }

    @Operation(summary = "1. Create new employee", description = "Creates and saves a new employee record.")
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @Operation(summary = "4. Update employee", description = "Updates an existing employee's data using their ID.")
    @PutMapping("/{empID}")
    public Employee updateEmployee(@PathVariable String empID, @RequestBody Employee updatedEmployee) {
        return employeeService.updateEmployee(updatedEmployee, empID);
    }

    @Operation(summary = "5. Delete employee", description = "Deletes an employee record using their ID.")
    @DeleteMapping("/{empID}")
    public void deleteEmployee(@PathVariable String empID) {
        employeeService.deleteEmployee(empID);
    }
}

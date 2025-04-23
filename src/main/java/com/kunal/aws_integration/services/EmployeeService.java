package com.kunal.aws_integration.services;

import com.kunal.aws_integration.model.Employee;
import com.kunal.aws_integration.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee updatedEmployee, String empID) {

        try {
            Optional<Employee> dbEmployee = employeeRepository.findById(empID);

            if (dbEmployee.isPresent()) {
                dbEmployee.get().setEmail(updatedEmployee.getEmail() != null ? updatedEmployee.getEmail() : dbEmployee.get().getEmail());
                dbEmployee.get().setName(updatedEmployee.getName() != null ? updatedEmployee.getName() : dbEmployee.get().getName());
                dbEmployee.get().setRole(updatedEmployee.getRole() != null ? updatedEmployee.getRole() : dbEmployee.get().getRole());

                return employeeRepository.save(dbEmployee.get());

            }


            return null;
        } catch (Exception e) {
            log.error("Error updating employee: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void deleteEmployee(String empID) {
        employeeRepository.deleteById(empID);
    }

    public Employee getEmployee(String empID) {

        Optional<Employee> employee = employeeRepository.findById(empID);

        return employee.orElse(null);

    }
}

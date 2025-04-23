package com.kunal.aws_integration.servicesTest;

import com.kunal.aws_integration.model.Employee;
import com.kunal.aws_integration.repository.EmployeeRepository;
import com.kunal.aws_integration.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CreatingDemoData {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void createDemoData() {

        List<Employee> employees = new ArrayList<>();

        for (int i = 1; i <= 50; i++) {
            Employee employee = new Employee();
            employee.setName("Employee " + i);
            employee.setEmail("emp" + i + "@emp.com");
            employee.setRole("developer");

            employees.add(employee);
        }

        assertNotNull(employeeRepository.saveAll(employees));
    }

}

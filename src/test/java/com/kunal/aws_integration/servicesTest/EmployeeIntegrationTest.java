package com.kunal.aws_integration.servicesTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kunal.aws_integration.model.Employee;
import com.kunal.aws_integration.repository.EmployeeRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@Transactional
class EmployeeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

//    @Autowired
    private EmployeeRepository repository;

    @Autowired
    public EmployeeIntegrationTest(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private ObjectMapper objectMapper;

//    private Employee saved;

    @Test
    @Order(1)
    void createEmployeeTest() throws Exception {
        Employee emp = new Employee("Test", "test@test.com", "Developer");

        mockMvc.perform(post("/api/emp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emp)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test"));
    }

    @Test
    @Order(2)
    void getEmployeeTest() throws Exception {
        Employee save = repository.save(new Employee("Test", "test@test.com", "Developer"));

        mockMvc.perform(get("/api/emp/" + save.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test"));
    }

    @Test
    @Order(3)
    void updateEmployeeTest() throws Exception {
        Employee saved = repository.save(new Employee("Test", "test@test.com", "Developer"));

        Employee updated = new Employee( "Test2","test2@test.com", "Senior Developer");

        mockMvc.perform(put("/api/emp/" + saved.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.role").value("Senior Developer"));
    }

    @Test
    @Order(4)
    void deleteEmployeeTest() throws Exception {
        Employee save = repository.save(new Employee("Test", "test@test.com", "Developer"));

        mockMvc.perform(delete("/api/emp/" + save.getId()))
                .andExpect(status().isOk());

        Optional<Employee> deleted = repository.findById(save.getId());
        Assertions.assertTrue(deleted.isEmpty());
    }

    @Test
    @Order(5)
    void getAllEmployeesTest() throws Exception {
        repository.save(new Employee("1", "A", "Dev"));
        repository.save(new Employee("2", "B", "Tester"));

        mockMvc.perform(get("/api/emp"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
}

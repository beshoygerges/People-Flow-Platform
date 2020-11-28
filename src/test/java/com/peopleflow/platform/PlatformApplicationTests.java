package com.peopleflow.platform;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peopleflow.platform.dto.EmployeeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PlatformApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addEmployeeReturnsOk() throws Exception {
        EmployeeDto employee = new EmployeeDto();
        employee.setAge(28);
        employee.setName("Beshoy Gerges Yacoub");
        employee.setStartDate(LocalDate.of(2020, 11, 28));

        mockMvc.perform(post("/rest/v1/employees")
                .contentType("application/json")
                .accept("application/json")
                .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk());
    }

    @Test
    void addEmployeeWithoutNameReturnsBadRequest() throws Exception {
        EmployeeDto employee = new EmployeeDto();
        employee.setAge(28);
        employee.setStartDate(LocalDate.of(2020, 11, 28));

        mockMvc.perform(post("/rest/v1/employees")
                .contentType("application/json")
                .accept("application/json")
                .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void EditEmployeeReturnsOk() throws Exception {
        EmployeeDto employee = new EmployeeDto();
        employee.setAge(28);
        employee.setName("Beshoy Gerges Yacoub");
        employee.setStartDate(LocalDate.of(2020, 11, 28));


        MvcResult result = mockMvc.perform(post("/rest/v1/employees")
                .contentType("application/json")
                .accept("application/json")
                .content(objectMapper.writeValueAsString(employee)))
                .andReturn();

        EmployeeDto employeeDto = objectMapper.readValue(result.getResponse().getContentAsString(), EmployeeDto.class);

        mockMvc.perform(put("/rest/v1/employees/{id}", employeeDto.getId() + "")
                .accept("application/json")
                .param("state", "APPROVED"))
                .andExpect(status().isOk());
    }

    @Test
    void editNonExistEmployeeReturnsBadRequest() throws Exception {
        mockMvc.perform(put("/rest/v1/employees/{id}", "100")
                .accept("application/json")
                .param("state", "APPROVED"))
                .andExpect(status().isBadRequest());
    }

}

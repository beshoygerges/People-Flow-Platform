package com.peopleflow.platform.controller;

import com.peopleflow.platform.dto.EmployeeDto;
import com.peopleflow.platform.model.values.MachineState;
import com.peopleflow.platform.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/rest/v1/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;


    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeDto>> getEmployees() {
        return ResponseEntity.ok(employeeService.getEmployees());
    }

    @ResponseStatus(code = CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody @Valid final EmployeeDto employee) {
        return ResponseEntity.ok(employeeService.addEmployee(employee));
    }

    @PutMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable final Integer id, @RequestParam final MachineState state) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, state));
    }


}

package com.peopleflow.platform.service;

import com.peopleflow.platform.dto.EmployeeDto;
import com.peopleflow.platform.model.values.MachineState;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> getEmployees();

    EmployeeDto addEmployee(final EmployeeDto employee);

    EmployeeDto updateEmployee(final Integer id, final MachineState state);
}

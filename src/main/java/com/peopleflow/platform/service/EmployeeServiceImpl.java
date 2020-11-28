package com.peopleflow.platform.service;

import com.peopleflow.platform.dto.EmployeeDto;
import com.peopleflow.platform.model.Employee;
import com.peopleflow.platform.model.values.MachineState;
import com.peopleflow.platform.repository.EmployeeRepository;
import com.peopleflow.platform.util.MapperUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDto> getEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(e -> MapperUtil.map(e, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto addEmployee(final EmployeeDto employeeDto) {
        Employee employee = employeeRepository.save(MapperUtil.map(employeeDto, Employee.class));
        return MapperUtil.map(employee, EmployeeDto.class);
    }

    @Transactional
    @Override
    public EmployeeDto updateEmployee(final Integer id, final MachineState state) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        Employee employee = optionalEmployee.orElseThrow(() -> new RuntimeException("Employee not exist"));
        if (employee.getState() == state)
            throw new RuntimeException("Employee state's is already " + state);
        employee.setState(state);
        return MapperUtil.map(employee, EmployeeDto.class);
    }
}

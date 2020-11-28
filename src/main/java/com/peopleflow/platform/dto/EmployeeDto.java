package com.peopleflow.platform.dto;

import com.peopleflow.platform.model.values.MachineState;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class EmployeeDto implements Serializable {

    private Integer id;

    @NotEmpty
    private String name;

    @NotNull
    private Integer age;

    @NotNull
    private LocalDate startDate;

    private MachineState state = MachineState.ADDED;
}

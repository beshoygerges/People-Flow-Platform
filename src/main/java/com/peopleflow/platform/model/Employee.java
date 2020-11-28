package com.peopleflow.platform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.peopleflow.platform.model.values.MachineState;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Employee")
@Data
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private MachineState state = MachineState.ADDED;

    @JsonIgnore
    @CreationTimestamp
    private LocalDate createdAt;

    @JsonIgnore
    @UpdateTimestamp
    private LocalDate updatedAt;
}

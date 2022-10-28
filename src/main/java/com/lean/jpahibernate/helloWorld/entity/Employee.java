package com.lean.jpahibernate.helloWorld.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING) //-> query to DB is String type (PART_TIME or FULL_TIME, CONTRACT)
//    @Enumerated(EnumType.ORDINAL) -> query to DB is Integer type
//    @Enumerated  -> if not have EnumType, default save to DB is 1,2,3,...
    @Column(name = "status")
    private EmployeeStatus employeeStatus;

}

package com.lean.jpahibernate.helloWorld.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "guide")
@Data
public class Guide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private Long salary;

    @OneToMany(mappedBy = "guide", cascade = CascadeType.ALL)
    private Set<Student> students = new HashSet<>();
}

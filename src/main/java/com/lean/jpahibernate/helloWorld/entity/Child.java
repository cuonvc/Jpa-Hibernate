package com.lean.jpahibernate.helloWorld.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "child")
@Data
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "firstname_fk", referencedColumnName = "firstname"),
            @JoinColumn(name = "lastname_fk", referencedColumnName = "lastname")
    })
    private Parent parent;

}

package com.lean.jpahibernate.helloWorld.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "parent")
@Data
public class Parent {

    @EmbeddedId
    private ParentPrimaryKey parentPrimaryKey;

    private Integer age;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.PERSIST)
    private Set<Child> children = new HashSet<>();

}

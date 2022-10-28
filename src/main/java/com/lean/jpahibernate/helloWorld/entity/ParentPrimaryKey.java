package com.lean.jpahibernate.helloWorld.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@EqualsAndHashCode
public class ParentPrimaryKey implements Serializable {

    private String firstname;
    private String lastname;

}

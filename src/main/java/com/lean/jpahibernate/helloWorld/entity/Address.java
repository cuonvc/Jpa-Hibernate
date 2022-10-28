package com.lean.jpahibernate.helloWorld.entity;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Address {

    private String zipcode;
    private String street;
    private String city;
}

package com.lean.jpahibernate.helloWorld.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable  //component class (Not entity)
@Data
public class Address {

    @Column(name = "home_street")
    private String street;

    @Column(name = "home_city")
    private String city;

    @Column(name = "home_zipcode")
    private String zipcode;
}

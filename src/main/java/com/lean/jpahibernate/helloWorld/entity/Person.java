package com.lean.jpahibernate.helloWorld.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private Integer age;

    @Embedded
    //default (not have @AttributeOverrides: auto select to @Column in Address class)
    private Address homeAddress;  //component class (belong to Person entity)

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "billing_street")),
            @AttributeOverride(name = "city", column = @Column(name = "billing_city")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "billing_zipcode"))
    })
    private Address billingAddress;  //component class (belong to Person entity)


    //table after generated: id | first_name | last_name | age | home_street | home_city | home_zipcode | billing_street | billing_city | billing_zipcode

}

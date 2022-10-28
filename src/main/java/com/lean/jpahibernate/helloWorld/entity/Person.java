package com.lean.jpahibernate.helloWorld.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "person")
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @ElementCollection
    @CollectionTable(name = "person_nickname",
            joinColumns = @JoinColumn(name = "person_id")
    )
    @Column(name = "nick_name") // -> column of "person_nickname" table (not "person" table)
    private Collection<String> nicknames = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "person_address",
            joinColumns = @JoinColumn(name = "person_id")
    )
    @AttributeOverrides({
            @AttributeOverride(name = "zipcode", column = @Column(name = "address_zipcode")),
            @AttributeOverride(name = "street", column = @Column(name = "address_street")),
            @AttributeOverride(name = "city", column = @Column(name = "address_city"))
    })
    private Collection<Address> addresses = new ArrayList<>();
}

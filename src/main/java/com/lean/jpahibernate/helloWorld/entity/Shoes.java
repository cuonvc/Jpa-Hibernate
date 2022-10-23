package com.lean.jpahibernate.helloWorld.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "shoes")
//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Shoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "height")
    private Float height;

    @Column(name = "color")
    private String color;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "person_id")
    private Person person;

}

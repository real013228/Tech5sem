package org.real013228.dal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@Entity
@Getter
@Setter
public class Cat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Calendar birthDate;
    private String breed;
    private String color;
    @ManyToOne
    private Owner owner;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cat> friends;
}

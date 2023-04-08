package com.real013228.entity;

import jakarta.persistence.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
public class CatEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date birthDate;
    private String breed;
    private String color;
    @ManyToOne
    private OwnerEntity owner;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<CatEntity> friends;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public String getBreed() {
        return this.breed;
    }

    public String getColor() {
        return this.color;
    }

    public OwnerEntity getOwner() {
        return this.owner;
    }

    public List<CatEntity> getFriends() {
        return this.friends;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setOwner(OwnerEntity owner) {
        this.owner = owner;
    }

    public void setFriends(List<CatEntity> friends) {
        this.friends = friends;
    }
}

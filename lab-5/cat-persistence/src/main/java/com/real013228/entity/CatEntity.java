package com.real013228.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date birthDate;
    private String breed;
    private String color;
    private Long owner;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<CatEntity> friends;
}

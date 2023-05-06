package com.real013228.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OwnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String birthDate;
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<CatEntity> cats;
    private Long userAccount;
}

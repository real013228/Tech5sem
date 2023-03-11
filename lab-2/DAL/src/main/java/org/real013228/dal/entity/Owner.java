package org.real013228.dal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.naming.directory.SearchResult;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@Entity
@Getter
@Setter
public class Owner implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Calendar birthDate;
    @OneToMany(mappedBy = "ownerId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cat> cats;
}

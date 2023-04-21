package com.real013228.entity;

import jakarta.persistence.*;

import java.util.List;

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
    @JoinColumn(name = "owner_id")
    private List<CatEntity> cats;
    @OneToOne(cascade = CascadeType.ALL
    )
    private UserEntity userAccount;

    public OwnerEntity() {}

    OwnerEntity(Long id, String name, String birthDate, List<CatEntity> cats, UserEntity userAccount) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.cats = cats;
        this.userAccount = userAccount;
    }

    public static OwnerEntityBuilder builder() {
        return new OwnerEntityBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public List<CatEntity> getCats() {
        return this.cats;
    }

    public UserEntity getUserAccount() {
        return this.userAccount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setCats(List<CatEntity> cats) {
        this.cats = cats;
    }

    public void setUserAccount(UserEntity userAccount) {
        this.userAccount = userAccount;
    }

    public static class OwnerEntityBuilder {
        private Long id;
        private String name;
        private String birthDate;
        private List<CatEntity> cats;
        private UserEntity userAccount;

        OwnerEntityBuilder() {
        }

        public OwnerEntityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public OwnerEntityBuilder name(String name) {
            this.name = name;
            return this;
        }

        public OwnerEntityBuilder birthDate(String birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public OwnerEntityBuilder cats(List<CatEntity> cats) {
            this.cats = cats;
            return this;
        }

        public OwnerEntityBuilder userAccount(UserEntity userAccount) {
            this.userAccount = userAccount;
            return this;
        }

        public OwnerEntity build() {
            return new OwnerEntity(id, name, birthDate, cats, userAccount);
        }

        public String toString() {
            return "OwnerEntity.OwnerEntityBuilder(id=" + this.id + ", name=" + this.name + ", birthDate=" + this.birthDate + ", cats=" + this.cats + ", userAccount=" + this.userAccount + ")";
        }
    }
}

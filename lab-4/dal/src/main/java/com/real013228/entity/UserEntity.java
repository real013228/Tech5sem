package com.real013228.entity;

import com.real013228.ERole;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;
    private String login;
    private String password;
    private String firstname;
    private String lastname;
    @Enumerated(EnumType.STRING)
    private ERole role;
    @OneToOne(mappedBy = "userAccount")
    private OwnerEntity owner;

    private UserEntity(Long id, String login, String password, String firstname, String lastname, ERole role, OwnerEntity owner) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
        this.owner = owner;
    }

    public UserEntity() {
    }

    public static UserEntityBuilder builder() {
        return new UserEntityBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getLogin() {
        return this.login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public ERole getRole() {
        return this.role;
    }

    public OwnerEntity getOwner() {
        return this.owner;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setRole(ERole role) {
        this.role = role;
    }

    public void setOwner(OwnerEntity owner) {
        this.owner = owner;
    }

    public static class UserEntityBuilder {
        private Long id;
        private String login;
        private String password;
        private String firstname;
        private String lastname;
        private ERole role;
        private OwnerEntity owner;

        UserEntityBuilder() {
        }

        public UserEntityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserEntityBuilder login(String login) {
            this.login = login;
            return this;
        }

        public UserEntityBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserEntityBuilder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public UserEntityBuilder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public UserEntityBuilder role(ERole role) {
            this.role = role;
            return this;
        }

        public UserEntityBuilder owner(OwnerEntity owner) {
            this.owner = owner;
            return this;
        }

        public UserEntity build() {
            return new UserEntity(id, login, password, firstname, lastname, role, owner);
        }

        public String toString() {
            return "UserEntity.UserEntityBuilder(id=" + this.id + ", login=" + this.login + ", password=" + this.password + ", firstname=" + this.firstname + ", lastname=" + this.lastname + ", role=" + this.role + ", owner=" + this.owner + ")";
        }
    }
}

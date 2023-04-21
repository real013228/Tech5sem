package com.real013228.entity;

import com.real013228.ERole;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Builder
@RequiredArgsConstructor
public class UserEntity implements UserDetails {
    @Id
    private Long id;
    private String login;
    private String password;
    private String firstname;
    private String lastname;
    @Enumerated(EnumType.STRING)
    private ERole role;
    @OneToOne(mappedBy = "userAccount")
    private OwnerEntity owner;

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
}

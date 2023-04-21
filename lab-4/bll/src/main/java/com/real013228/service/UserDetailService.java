package com.real013228.service;

import com.real013228.Mapper;
import com.real013228.dto.RegisterRequest;
import com.real013228.entity.UserEntity;
import com.real013228.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Component
public class UserDetailService implements UserDetailsService {
    private final UserRepository repository;

    public UserDetailService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repository.findByLogin(username).orElse(null);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("User isn't found");

        }

        return new User(user.getLogin(), user.getPassword(), true, true, true, true, new HashSet<>());
    }
}

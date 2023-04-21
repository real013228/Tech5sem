package com.real013228.service;

import com.real013228.ERole;
import com.real013228.dto.AuthenticationRequest;
import com.real013228.dto.AuthenticationResponse;
import com.real013228.dto.RegisterRequest;
import com.real013228.entity.OwnerEntity;
import com.real013228.entity.UserEntity;
import com.real013228.repository.OwnerRepository;
import com.real013228.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final OwnerRepository ownerRepository;
    private final PasswordEncoder encoder;
    private final JwtService service;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, OwnerRepository ownerRepository, PasswordEncoder encoder, JwtService service, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.ownerRepository = ownerRepository;
        this.encoder = encoder;
        this.service = service;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByLogin(request.getLogin()).orElseThrow();
        var jwtToken = service.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse register(RegisterRequest request) {
        UserEntity user = null;
        if (request.getRole() == ERole.ROLE_OWNER) {
            var owner = OwnerEntity.builder()
                    .name(request.getFirstName())
                    .cats(new ArrayList<>())
                    .birthDate(String.valueOf(new Date(System.currentTimeMillis())))
                    .build();
            ownerRepository.save(owner);
            user = UserEntity.builder()
                    .firstname(request.getFirstName())
                    .lastname(request.getLastName())
                    .login(request.getLogin())
                    .password(encoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .owner(owner)
                    .build();
            owner.setUserAccount(user);
            ownerRepository.save(owner);
        } else if (request.getRole() == ERole.ROLE_ADMIN){
            user = UserEntity.builder()
                    .firstname(request.getFirstName())
                    .lastname(request.getLastName())
                    .login(request.getLogin())
                    .password(encoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .build();
        }
        var jwtToken = service.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}

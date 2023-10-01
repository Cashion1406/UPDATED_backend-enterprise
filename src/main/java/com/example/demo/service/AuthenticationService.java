package com.example.demo.service;


import com.example.demo.DTO.Authentication.AuthenticationRequest;
import com.example.demo.DTO.Authentication.AuthenticationResponse;
import com.example.demo.DTO.RegisterRequest;
import com.example.demo.model.Client;
import com.example.demo.model.ERole;
import com.example.demo.repo.ClientRepo;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final ClientRepo clientRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        Client newClient = new Client();
        newClient.setId(request.getId());
        newClient.setFirstname(request.getFirstname());
        newClient.setLastname(request.getLastname());
        newClient.setEmail(request.getEmail());
        newClient.setRole(ERole.USER);
        newClient.setPassword(passwordEncoder.encode(request.password));
        newClient.setIsDeleted(false);
        clientRepo.save(newClient);

        var jwtToken = jwtService.generateToken(newClient);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authen(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        var client = clientRepo.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(client);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}

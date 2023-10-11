package com.example.demo.service;


import com.example.demo.DTO.Authentication.AuthenticationRequest;
import com.example.demo.DTO.Authentication.AuthenticationResponse;
import com.example.demo.DTO.RegisterRequest;
import com.example.demo.model.Client;
import com.example.demo.model.ERole;
import com.example.demo.model.EToken;
import com.example.demo.model.Token;
import com.example.demo.repo.ClientRepo;
import com.example.demo.repo.TokenRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final ClientRepo clientRepo;
    private final TokenRepo tokenRepo;
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
        var refreshToken = jwtService.generateRefreshToken(newClient);
        saveUserToken(newClient, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(Client client, String jwtToken) {
        var token = new Token();
        token.setToken(jwtToken);
        token.setTokenType(EToken.BEARER);
        token.setClient(client);
        token.setExpired(false);
        token.setRevoked(false);
        tokenRepo.save(token);
    }

    //Invalidate user token when logout
    private void revokeUserTokens(Client client) {
        var validTokens = tokenRepo.findALlValidToken(client.getId());

        if (validTokens.isEmpty()) return;

        validTokens.forEach(token -> {

            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepo.saveAll(validTokens);

    }


    //Check user credential with DB
    public AuthenticationResponse authen(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        var client = clientRepo.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(client);
        revokeUserTokens(client);
        var refreshToken = jwtService.generateRefreshToken(client);

        saveUserToken(client, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public void refresh(HttpServletRequest request, HttpServletResponse response) throws IOException {


        final String authenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String email;


        //Authen header mustn't null and contain Bearer
        if (authenHeader == null || !authenHeader.startsWith("Bearer ")) {
            return;

            //Stop the execution of the filter
        }

        //Extract the token from bearer || Bearer with a space contains 7 characters
        refreshToken = authenHeader.substring(7);
        email = jwtService.getEmailFromJwt(refreshToken);
        if (email != null) {

            var userDetail = clientRepo.findByEmail(email).orElseThrow();
//            saveUserToken(userDetail, refreshToken);
//
//            //Check refresh token valid or not
//            var validToken = tokenRepo.findByToken(refreshToken)
//                    .map(token -> !token.getExpired() && !token.getRevoked())
//                    .orElse(false);

            //Check valid token and revoked  token in the db
            if (jwtService.isTokenValid(refreshToken, userDetail)) {

                var accessToken = jwtService.generateToken(userDetail);

                revokeUserTokens(userDetail);

                saveUserToken(userDetail, accessToken);

                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();


                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}

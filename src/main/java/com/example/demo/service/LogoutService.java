package com.example.demo.service;

import com.example.demo.model.Token;
import com.example.demo.repo.TokenRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {


    private final TokenRepo tokenRepo;

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {

        final String authenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String jwt;

        //Authen header mustn't null and contain Bearer
        if (authenHeader == null || !authenHeader.startsWith("Bearer ")) {
            return;

        }

        //Extract the token from bearer || Bearer with a space contains 7 characters
        jwt = authenHeader.substring(7);


        //Get saved token from db
        Token savedToken = tokenRepo.findByToken(jwt).get();


        //If exist token is found, invalidate the token
        if (savedToken != null) {
            savedToken.setExpired(true);
            savedToken.setRevoked(true);

            tokenRepo.save(savedToken);
        }
    }
}

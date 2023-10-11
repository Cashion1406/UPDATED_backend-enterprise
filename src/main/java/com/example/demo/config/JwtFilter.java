package com.example.demo.config;

import com.example.demo.repo.TokenRepo;
import com.example.demo.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {


    private final JwtService jwtService;
    private final TokenRepo tokenRepo;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authenHeader = request.getHeader("Authorization");
        final String jwt;
        final String email;


        //Authen header mustn't null and contain Bearer
        if (authenHeader == null || !authenHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;

            //Stop the execution of the filter
        }

        //Extract the token from bearer || Bearer with a space contains 7 characters
        jwt = authenHeader.substring(7);
        email = jwtService.getEmailFromJwt(jwt);
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetail = userDetailsService.loadUserByUsername(email);

            var validToken = tokenRepo.findByToken(jwt)
                    .map(token -> !token.getExpired() && !token.getRevoked())
                    .orElse(false);

            //Check valid token and revoked token in the db
            if (jwtService.isTokenValid(jwt, userDetail) && validToken) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetail,
                        null,
                        userDetail.getAuthorities());

                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}

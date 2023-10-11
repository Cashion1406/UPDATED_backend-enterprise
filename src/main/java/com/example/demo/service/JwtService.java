package com.example.demo.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    @Value("${security.jwt.secret-key.expiration}")
    private long jwtExp;

    @Value("${security.jwt.refresh-token.expiration}")
    private long refreshTokenExp;


    public String getEmailFromJwt(String token) {
        return extractClaim(token, Claims::getSubject);

    }


    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
//
//        Calendar calendar = Calendar.getInstance();
//        Date now = calendar.getTime();
////
////        // Add one day to the current time to calculate the expiration time
////        calendar.setTime(now);
////        calendar.add(Calendar.DAY_OF_MONTH, 1);
////        Date expirationTime = calendar.getTime();

        extraClaims.put("password",userDetails.getPassword());
        return buildToken(extraClaims, userDetails, jwtExp);
    }

    public String generateRefreshToken(UserDetails userDetails) {


        return buildToken(new HashMap<>(), userDetails, refreshTokenExp);
    }

    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expirationTime) {
        return Jwts
                .builder().setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        // Parse the JWT token to get the claim
        final Claims claims = getAllClaims(token);

        // Extract and process the desired claim
        return claimsResolver.apply(claims);
    }

    // Parse the JWT token and get the claims
    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    // Get signing key
    private Key getSigninKey() {

        // Decode the secret key and create a signing key
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String email = getEmailFromJwt(token);
        return (email.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}

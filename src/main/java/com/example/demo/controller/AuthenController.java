package com.example.demo.controller;

import com.example.demo.DTO.Authentication.AuthenticationRequest;
import com.example.demo.DTO.Authentication.AuthenticationResponse;
import com.example.demo.DTO.RegisterRequest;
import com.example.demo.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/authen")
@RequiredArgsConstructor
public class AuthenController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {


        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {


        return ResponseEntity.ok(authenticationService.authen(request));
    }

    @PostMapping("/refresh")
    @CrossOrigin(origins = "http://localhost:3000")
    public void refresh(HttpServletRequest request, HttpServletResponse response) throws IOException {


        authenticationService.refresh(request, response);
    }
}

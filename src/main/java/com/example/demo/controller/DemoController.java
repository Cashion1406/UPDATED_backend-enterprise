package com.example.demo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test-controller")
public class DemoController {

    @GetMapping
    public ResponseEntity<String> test() {

        return ResponseEntity.ok("SECURED ENDPOINT");
    }
}

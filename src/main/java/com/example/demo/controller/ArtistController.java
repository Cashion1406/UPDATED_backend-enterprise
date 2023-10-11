package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/artist")
@PreAuthorize("hasRole('ARTIST')")
public class ArtistController {
    @GetMapping
    @PreAuthorize("hasAnyAuthority('artist:read')")
    public String get() {
        return "Get some artist info";
    }

    @PutMapping
    @PreAuthorize("hasAuthority('artist:update')")
    public String put() {

        return "Update artist info";
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('artist:delete')")
    public String delete() {

        return "Delete artist info";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('artist:create')")
    public String post() {

        return "Post / Upload artist info";
    }
}
